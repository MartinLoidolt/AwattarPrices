import files.FileAccess;
import pojos.PriceEntry;

import java.io.File;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<PriceEntry> priceEntries = FileAccess.getTheInstance().loadPriceEntries();

        int yearStart = 2022;
        int yearEnd = 2023;

        int monthStart = 7;
        int monthEnd = 4;

        int hourStart = 8;
        int hourEnd = 22;

        int higherThen = -200;

        DoubleSummaryStatistics statistics = priceEntries.stream()
                //.filter(priceEntry -> priceEntry.getStart().getYear() >= yearStart && priceEntry.getStart().getYear() <= yearEnd)
                //.filter(priceEntry -> priceEntry.getStart().getMonthValue() >= monthStart || priceEntry.getStart().getMonthValue() <= monthEnd)
                //.filter(priceEntry -> priceEntry.getStart().getHour() >= hourStart && priceEntry.getStart().getHour() <= hourEnd)
                //.filter(priceEntry -> priceEntry.getBasePrice() > higherThen)
                .mapToDouble(PriceEntry::getBasePrice)
                .map(operand -> {
                    return operand * 1.2 + 1.44;
                })
                .summaryStatistics();

        double varianz = priceEntries.stream().mapToDouble(priceEntry -> Math.pow(priceEntry.getBasePrice() - statistics.getAverage(), 2)).average().getAsDouble();

        double sigma = Math.sqrt(Math.abs(varianz));

        System.out.println("Max: " + statistics.getMax());
        System.out.println("Min: " + statistics.getMin());
        System.out.println("Avg: " + statistics.getAverage());
        System.out.println("Count: " + statistics.getCount());
        System.out.println("Varianz: " + varianz);
        System.out.println("Sigma: " + sigma);

        //In 2.9% aller Stunden war Awattar teurer als unser Tarif


    }
}
