package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import roboscript.InputOutput;
import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.parser.ScriptCompiler;

public class GameBoard {

	private Cell[][] cells;
	private int numberOfCells, x_dimension, y_dimension;
	private static final double runtimeModifier = 1;
	List<Robot> bots, deadBots;
	Random rand;

	public GameBoard(int m, int n, double p) {
		System.out.println("Creating board of size "+m+"*"+n);
		rand = new Random();
		cells = new Cell[m][n];
		numberOfCells = m * n;
		x_dimension = m;
		y_dimension = n;

		bots = new ArrayList<Robot>();
		deadBots = new ArrayList<Robot>();
		Position.max_x = x_dimension - 1;
		Position.max_y = y_dimension - 1;

		System.out.println("done.");
		System.out.println("init cells...");
		initCells();
		System.out.println("done.");

		System.out.println("filling with food...");
		fillWithFood(p);
		System.out.println("done.");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}

	private void initCells() {
		// for progress calculation ____________
		int total = x_dimension * y_dimension;
		int amount = 0;
		double current_p = 0;
		double previous_p = 0;
		// _____________________________________

		System.out.println(Math.floor(current_p) + "%");
		for (int i = 0; i < x_dimension; i++) {
			for (int j = 0; j < y_dimension; j++) {
				Position pos = new Position(i, j);
				cells[i][j] = new Cell(pos);

				// for progress calculation ____________
				amount++;
				current_p = ((double) amount) / ((double) total) * 100;
				if (current_p >= (previous_p + 5)) {
					System.out.println(Math.floor(current_p) + "%");
					previous_p = current_p;
				}
				// _____________________________________
			}
		}
	}

	// fill percentage many cells with food
	private void fillWithFood(double percentage) {
		int toFill = (int) Math.floor(numberOfCells * percentage);

		// for progress calculation ____________
		int total = toFill;
		int amount = 0;
		double current_p = 0;
		double previous_p = 0;
		// _____________________________________

		while (toFill > 0) {

			int random_x = rand.nextInt(x_dimension);
			int random_y = rand.nextInt(y_dimension);
			cells[random_x][random_y].addFood(new Food(20, cells[random_x][random_y]));
			toFill--;

			// for progress calculation ____________
			amount++;
			current_p = ((double) amount) / ((double) total) * 100;
			if (current_p >= (previous_p + 5)) {
				System.out.println(Math.floor(current_p) + "%");
				previous_p = current_p;
			}
			// _____________________________________
		}

	}

	public void addRobotFromFile(String file) {
		Executable code = ScriptCompiler.compile("./scripts/test2.rs");
		String[] tokens = file.split("/");

		Robot bot = new Robot(code, tokens[tokens.length - 1]);
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

	public void printStatus(boolean printBoard, boolean printRobots) {
		if (printBoard) {
			printBoard();
		}
		System.out.println();
		System.out.println();

		if (printRobots) {
			printRobots();
		}
	}

	private void printRobots() {
		System.out.println("List of Bots:");
		System.out.println("id | energy | health | age");
		if (!bots.isEmpty()) {
			for (Robot bot : bots) {
				System.out.println(bot.getID() + " | " + bot.getEnergy() + " | " + bot.getHealth() + " | "
						+ bot.getAge());
			}
		} else {
			System.out.println("no bots on board");
		}
	}

	private void printBoard() {
		System.out.println("[X]=Bot,*=Food");

		for (int j = 0; j < x_dimension; j++) {
			for (int i = 0; i < y_dimension; i++) {
				if (cells[i][j].isOccupied()) {
					System.out.print("[" + cells[i][j].getOccupant().getID().charAt(0) + "] ");
				} else if (cells[i][j].hasFood()) {
					System.out.print("[*] ");
				} else {
					System.out.print("[ ] ");
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

			if (!cells[random_x][random_y].hasFood() && !cells[random_x][random_y].isOccupied()) {
				done = true;
				pos = new Position(random_x, random_y);
			}

		}

		return pos;
	}

	public void executeRobots() {
		for (Robot bot : bots) {
			// System.out.println("begin executing "+bot.getID());
			executeRobot(bot);
			// System.out.println("done executing "+bot.getID());
		}
		cleanDeadRobots();
	}

	private void moveRobot(Robot bot, int direction, double attack_strength) {
		if (direction == 0) {
			return;
		}
		Position old_pos = bot.getPosition().getPosition();
		Position new_pos = old_pos.move(direction);

		if (new_pos.isValid()) {
			Cell new_cell = cells[new_pos.x][new_pos.y];
			if (!new_cell.isOccupied()) {
				cells[old_pos.x][old_pos.y].deleteOccupant();
				new_cell.addBot(bot);
				bot.setPosition(new_cell);
			} else {
				// TODO attack move on new_cell with attack_strength
				deadBots.add(bot);
			}
		} else {
			deadBots.add(bot);
		}
	}

	private void executeRobot(Robot bot) {
		long executeTime = 0;

		try {
			// System.out.println("starting executable");
			executeTime = bot.execute();
			// System.out.println("done executable");
		} catch (ExecuteException e) {
			System.out.println(bot.getID() + "exception: " + e.getMessage());
			deadBots.add(bot);
			return;
		}

		double energyLoss = executeTime * runtimeModifier;
		System.out.println("energy loss: " + energyLoss);
		if (energyLoss < 10) {
			bot.loseEnergy(10);
		} else {
			bot.loseEnergy((int) energyLoss);
		}

		if (bot.getEnergy() <= 0) {
			deadBots.add(bot);
			return;
		}
		
		int direction = InputOutput.getOutputMoveDirection(bot);
		double attack_strength = InputOutput.getAttackStrength(bot);
		System.out.println("!     new direction: " + Position.getCellName(direction));
		moveRobot(bot, direction, attack_strength);
		bot.incrementAge();
	}

}
