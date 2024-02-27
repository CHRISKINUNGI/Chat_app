// ChartManager.java
import javafx.scene.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ChartManager {
    private JFreeChart chart;

    public ChartManager() {
        createBarChart();
    }

    private void createBarChart() {
        CategoryDataset dataset = createSampleCategoryDataset();
        chart = ChartFactory.createBarChart(
                "Sample Bar Chart",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private void createLineChart() {
        CategoryDataset dataset = createSampleCategoryDataset();
        chart = ChartFactory.createLineChart(
                "Sample Line Chart",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private void createPieChart() {
        DefaultPieDataset dataset = createSamplePieDataset();
        chart = ChartFactory.createPieChart(
                "Sample Pie Chart",
                dataset,
                true,
                true,
                false
        );
    }

    private CategoryDataset createSampleCategoryDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Series1", "Category1");
        dataset.addValue(20, "Series1", "Category2");
        dataset.addValue(15, "Series2", "Category1");
        dataset.addValue(25, "Series2", "Category2");
        return dataset;
    }

    private DefaultPieDataset createSamplePieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category1", 10);
        dataset.setValue("Category2", 20);
        dataset.setValue("Category3", 15);
        return dataset;
    }

    public Node getChartNode() {
        return new ChartPanel(chart);
    }

    public void updateChart(String chartType) {
        switch (chartType) {
            case "Bar Chart":
                createBarChart();
                break;
            case "Line Chart":
                createLineChart();
                break;
            case "Pie Chart":
                createPieChart();
                break;
            default:
                System.out.println("Invalid chart type");
        }
    }
}
