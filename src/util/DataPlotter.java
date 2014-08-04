package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DataPlotter extends ApplicationFrame {

	/**
	 * A demonstration application showing an XY series containing a null value.
	 *
	 * @param title
	 *            the frame title.
	 */
	public DataPlotter(final String title,String xAxis,String yAxis, XYSeries plotdata) {

		super(title);
		final XYSeriesCollection data = new XYSeriesCollection(plotdata);
		final JFreeChart chart = ChartFactory.createXYAreaChart(
				title, xAxis, yAxis, data, PlotOrientation.VERTICAL,
				true, true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}


	
	
	public static void showData(String title,String xAxis,String yAxis,XYSeries data){
		final DataPlotter frame = new DataPlotter(title,xAxis,yAxis,data);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
		
	}
	
	
	
}