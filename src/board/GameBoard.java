package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.parser.ScriptCompiler;

public class GameBoard {

	private Cell[][] cells;
	private int numberOfCells, x_dimension, y_dimension;
	List<Robot> bots, deadBots;
	Random rand;

	public GameBoard(int m, int n, double p) {
		rand = new Random();
		cells = new Cell[m][n];
		numberOfCells = m * n;
		x_dimension = m;
		y_dimension = n;
		initCells();
		fillWithFood(p);
		bots = new ArrayList<Robot>();
		deadBots = new ArrayList<Robot>();
		Position.max_x = x_dimension - 1;
		Position.max_y = y_dimension - 1;
	}

	private void initCells() {
		for (int i = 0; i < x_dimension; i++) {
			for (int j = 0; j < y_dimension; j++) {
				Position pos = new Position(i, j);
				cells[i][j] = new Cell(pos);
			}
		}
	}

	// fill percentage many cells with food
	private void fillWithFood(double percentage) {
		int toFill = (int) Math.floor(numberOfCells * percentage);

		while (toFill > 0) {

			int random_x = rand.nextInt(x_dimension);
			int random_y = rand.nextInt(y_dimension);
			cells[random_x][random_y].addFood(new Food(20, cells[random_x][random_y]));
			toFill--;

		}

	}

	public void addRobotFromFile(String file) {
		Executable code = ScriptCompiler.compile("./scripts/test2.rs");
		String[] tokens = file.split("/");
		
		Robot bot = new Robot(code,tokens[tokens.length-1]);
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

	public void printBoard() {
		System.out.println("[X]=Bot,*=Food");

		for (int j = 0; j < x_dimension; j++) {
			for (int i = 0; i < y_dimension; i++) {
				if (cells[i][j].isOccupied()) {
					System.out.print("["+cells[i][j].getOccupant().getID().charAt(0) +"] ");
				} else if (cells[i][j].hasFood()) {
					System.out.print("[*] ");
				} else {
					System.out.print("[ ] ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println("List of Bots:");
		if (!bots.isEmpty()) {
			for (Robot bot : bots) {
				System.out.println(bot.getID() + " Energy: " + bot.getEnergy());
			}
		} else {
			System.out.println("no bots on board");
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

	private void moveRobot(Robot bot, int direction) {
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
				//TODO attack move on new_cell
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
			deadBots.add(bot);
			return;
		}

		// TODO subtract energy according to runtime
		bot.loseEnergy(10);

		if (bot.getEnergy() <= 0) {
			deadBots.add(bot);
			return;
		}
		int x = rand.nextInt(9);
		//System.out.println("new direction " + x);
		moveRobot(bot, x);
		bot.incrementAge();
	}

}
