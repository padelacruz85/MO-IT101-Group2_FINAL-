package main;

import java.text.DecimalFormat;

public abstract class CalculationFormat {
    protected static final DecimalFormat CalcFormat = new DecimalFormat("#.##");
    protected abstract double WageCalculation();
    
}
