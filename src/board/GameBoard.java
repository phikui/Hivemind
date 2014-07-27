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
			cells[random_x][random_y].addFood(new Food(50, cells[random_x][random_y]));
			toFill--;

		}

	}

	public void addRobotFromFile(String file) {
		Executable code = ScriptCompiler.compile("./scripts/test2.rs");
		Robot bot = new Robot(code);
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
		System.out.println("B=Bot,O=empty,F=Food");

		for (int i = 0; i < x_dimension; i++) {
			for (int j = 0; j < y_dimension; j++) {
				if (cells[i][j].isOccupied()) {
					System.out.print("B ");
				} else if (cells[i][j].hasFood()) {
					System.out.print("F ");
				} else {
					System.out.print("O ");
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
			//System.out.println("begin executing "+bot.getID());
			executeMoveRobot(bot);
			//System.out.println("done executing "+bot.getID());
		}
		cleanDeadRobots();
	}

	private void executeMoveRobot(Robot bot) {
		long executeTime = 0;

		try {
			//System.out.println("starting executable");
			executeTime = bot.execute();
			//System.out.println("done executable");
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

	}

}
