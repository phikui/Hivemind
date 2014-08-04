package board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.jfree.data.xy.XYSeries;

import board.score.AccumulatedScore;
import board.score.Score;
import board.score.ScoreComparator;
import roboscript.InputOutput;
import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.parser.ScriptCompiler;
import util.Progress;

public class GameBoard {

	protected Cell[][] cells;
	private int numberOfCells, x_dimension, y_dimension;

	@SuppressWarnings("unused")
	private static final double runtimeModifier = 1;
	private List<Robot> bots, deadBots;
	private Random rand;
	private StringBuffer events;
	private List<Score> highScore;
	private int age;
	private XYSeries population;

	public int getNumberOfCells() {
		return numberOfCells;
	}

	public int getX_dimension() {
		return x_dimension;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getY_dimension() {
		return y_dimension;
	}

	protected Cell getCellFromPosition(Position pos) {
		if (pos.isValid(this)) {
			return cells[pos.x][pos.y];
		} else {
			return null;
		}
	}

	public int getAge() {
		return age;
	}

	public void addMultipleBots(String[] bots, int swarmSize) {
		System.out.println("adding bots: ");
		Progress p = new Progress(bots.length * swarmSize, "Adding Bots");
		for (int i = 0; i < bots.length; i++) {
			for (int j = 0; j < swarmSize; j++) {
				addRobotFromFile(bots[i]);
				p.increment(1);
				// p.printProgress();
			}
		}
		System.out.println("done.");
	}

	private int countNonNullCells() {
		int result = 0;
		for (int i = 0; i < x_dimension; i++) {
			for (int j = 0; j < y_dimension; j++) {
				if (cells[i][j] != null) {
					result++;
				}
			}
		}
		return result;
	}

	public GameBoard(int m, int n, double p) {
		System.out.println("Creating board of size " + m + "*" + n);
		age = 0;
		highScore = new ArrayList<Score>();
		rand = new Random();
		cells = new Cell[m][n];
		numberOfCells = m * n;
		x_dimension = m;
		y_dimension = n;
		population = new XYSeries("Living Bots");

		events = new StringBuffer();
		bots = new ArrayList<Robot>();
		deadBots = new ArrayList<Robot>();
		Position.max_x = x_dimension - 1;
		Position.max_y = y_dimension - 1;

		System.out.println("done.");
		System.out.println("init cells...");
		initCells();
		System.out.println("done.");

		System.out.print("creating some canyons...");
		for (int i = 0; i < 5; i++) {
			generateCanyons((int) (0.3 * n * m), 3, findRandomEmptyPosition());
		}
		System.out.println(" done.");

		numberOfCells = countNonNullCells();

		System.out.println("filling with food...");
		fillWithFood(p);
		System.out.println("done.");
	}

	private void generateCanyons(int length, int width, Position start) {
		Position pos = start;
		List<Position> path = pos.getRandomPath(30, this);
		List<Position> destroy = new ArrayList<Position>();
		for (Position x : path) {
			destroy.add(x);
			destroy.addAll(x.get_k_neighborhood(this, 1));
		}
		destroyCells(destroy);
	}

	private void initCells() {
		int total = x_dimension * y_dimension;
		Progress p = new Progress(total, "Creating Cells");

		for (int i = 0; i < x_dimension; i++) {
			for (int j = 0; j < y_dimension; j++) {
				Position pos = new Position(i, j);
				cells[i][j] = new Cell(pos);

				p.increment(1);

				// p.printProgress();
			}
		}
	}

	// fill percentage many cells with food
	private void fillWithFood(double percentage) {
		int toFill = (int) Math.floor(numberOfCells * percentage);
		Progress p = new Progress(toFill, "Creating Food");

		while (toFill > 0) {

			int random_x = rand.nextInt(x_dimension);
			int random_y = rand.nextInt(y_dimension);
			if (new Position(random_x, random_y).isValid(this)) {
				cells[random_x][random_y].addFood(new Food(10,
						cells[random_x][random_y]));
				toFill--;

				p.increment(1);
				// p.printProgress();

			}
		}

	}

	public void addRobotFromFile(String file) {
		addRobotFromFile(file, Color.BLACK);
	}

	public void addRobotFromFile(String file, Color printColor) {
		Executable code = ScriptCompiler.compile(file);
		if (code == null) {
			return;
		}
		String[] tokens = file.split("/");

		Robot bot = new Robot(code, tokens[tokens.length - 1]);
		bot.setPrintColor(printColor);
		addRobot(bot);
	}

	private void cleanDeadRobots() {
		for (Robot bot : deadBots) {
			bots.remove(bot);
			bot.getPosition().removeOccupant();
		}
		deadBots = new ArrayList<Robot>();
	}

	public void addRobot(Robot bot) {
		bots.add(bot);
		Position pos = findRandomEmptyPosition();
		cells[pos.x][pos.y].addBot(bot);
		bot.setPosition(cells[pos.x][pos.y]);
	}

	public void printStatus(boolean printBoard, boolean printRobots,
			boolean printEvents) {
		System.out.println("Age: " + age + "   Bots alive: " + bots.size());
		if (printBoard) {
			printBoard();
			System.out.println();

		}

		if (printRobots) {
			printRobots();
			System.out.println();
		}

		if (printEvents) {
			if (events.length() > 0) {
				System.out.println(events.toString());
				events = new StringBuffer();
				System.out.println();
			}
		}
	}

	private void printRobots() {
		System.out.println("List of Bots:");
		System.out.println("id | energy | health | age | origin");
		if (!bots.isEmpty()) {
			for (Robot bot : bots) {
				System.out.println(bot.getID() + " | " + bot.getEnergy()
						+ " | " + bot.getHealth() + " | " + bot.getAge()
						+ " | " + bot.getOrigin());
			}
		} else {
			System.out.println("no bots on board");
		}
	}

	private void printBoard() {
		System.out.println("[X]=Bot,*=Food");

		for (int j = 0; j < x_dimension; j++) {
			for (int i = 0; i < y_dimension; i++) {
				if (cells[i][j] != null) {
					if (cells[i][j].isOccupied()) {
						System.out.print("["
								+ cells[i][j].getOccupant().getID().charAt(0)
								+ "] ");
					} else if (cells[i][j].hasFood()) {
						System.out.print("[*] ");
					} else {
						System.out.print("[ ] ");
					}
				} else {
					System.out.print("    ");
				}
			}
			System.out.println();
		}

	}

	private Position findRandomEmptyPosition() {
		boolean done = false;
		Position pos = null;
		while (!done) {
			int random_x = rand.nextInt(x_dimension);
			int random_y = rand.nextInt(y_dimension);

			if (cells[random_x][random_y] != null) {
				if (!cells[random_x][random_y].hasFood()
						&& !cells[random_x][random_y].isOccupied()) {
					done = true;
					pos = new Position(random_x, random_y);
				}
			}

		}

		return pos;
	}

	public void executeRobots() {
		population.add(bots.size(), age);
		for (Robot bot : bots) {
			bot.setVariables(this);
			executeRobot(bot);
		}
		cleanDeadRobots();
		age++;
	}

	public XYSeries getPopulation() {
		return population;
	}

	private void killBot(Robot bot, String reason) {
		events.append(bot.getID() + " died because: " + reason + ", he left "
				+ bot.getEnergy() + " Engergy");
		events.append(System.getProperty("line.separator"));
		deadBots.add(bot);
		if (bot.getEnergy() > 0) {
			bot.getPosition().addFood(
					new Food(bot.getEnergy(), bot.getPosition()));
		}
		highScore.add(new Score(bot.getID(), bot.getOrigin(), bot.getAge(),
				reason));
	}

	private void moveRobot(Robot bot, int direction, int attack_strength) {
		if (direction == 0) {
			return;
		}
		Position old_pos = bot.getPosition().getPosition();
		Position new_pos = old_pos.move(direction);

		if (new_pos.isValid(this)) {
			Cell new_cell = cells[new_pos.x][new_pos.y];
			if (!new_cell.isOccupied()) {
				cells[old_pos.x][old_pos.y].deleteOccupant();
				new_cell.addBot(bot);
				bot.setPosition(new_cell);
			} else {
				if (attack_strength < 0) { // invalid
					killBot(bot, "Invalid attack strength");
					return;
				}

				int actual_attack = Math.min(bot.getEnergy() - attack_strength,
						0);
				bot.loseEnergy(attack_strength);
				if (bot.getEnergy() <= 0) {
					killBot(bot, "Energy <= 0");
					return;
				}
				Robot enemy = new_cell.getOccupant();
				enemy.loseHealth(actual_attack);
				// enemy has been defeated
				if (enemy.getHealth() <= 0) {
					killBot(enemy, "Health <= 0");
					new_cell.addBot(bot);
					bot.setPosition(new_cell);
				}

			}
		} else {
			killBot(bot, "Invalid Move");
		}
	}

	private void executeRobot(Robot bot) {
		@SuppressWarnings("unused")
		long executeTime = 0;

		try {

			executeTime = bot.execute();

		} catch (ExecuteException e) {

			killBot(bot, e.getMessage());
			return;
		}

		bot.loseEnergy(bot.getDefaultEnergyLoss());

		if (bot.getEnergy() <= 0) {
			killBot(bot, "Energy <= 0");
			return;
		}

		int direction = InputOutput.getOutputMoveDirection(bot);
		// System.out.println("direction: " + Position.getCellName(direction));
		int attack_strength = InputOutput.getAttackStrength(bot);
		moveRobot(bot, direction, attack_strength);
		bot.incrementAge();
	}

	public int getAlive() {

		return bots.size();
	}

	public void printHighScoreTopN(int n) {
		n = Math.min(n, highScore.size());
		Collections.sort(highScore, new ScoreComparator());
		System.out.println("highscore:");
		System.out.println("placement | age | id | origin | cause of Death");
		for (int i = 0; i < n; i++) {
			Score score = highScore.get(i);
			System.out.println((i+1) + " | " + score.age + " | " + score.id + " | "
					+ score.origin + " | " + score.causeOfDeath);
		}
	}

	public void printHighScore() {
		printHighScoreTopN(highScore.size());
	}

	public void printAverageScore() {
		HashMap<String, AccumulatedScore> acc_score = new HashMap<String, AccumulatedScore>();
		for (Score score : highScore) {
			acc_score.put(score.origin, new AccumulatedScore(score.origin,
					score.age));
		}
		System.out.println("origin | avarage age");
		for (String key : acc_score.keySet()) {
			AccumulatedScore score = acc_score.get(key);
			System.out.println(score.origin + " | " + score.age);
		}
	}

	public void destroyCells(List<Position> list) {
		for (Position pos : list) {
			if (pos.isValid(this)) {
				cells[pos.x][pos.y] = null;
			}
		}
	}

}
