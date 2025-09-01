# Grocery Calculator (CLI + Swing GUI)

Calculate the monthly total and weekly average grocery bill for a family of four, with and without a coupon.  
Coupon is entered as a decimal(e.g., `.10` for 10%); if invalid (≤ 0 or > 1), it defaults to 10%.

---

## Features
- Prompt for coupon (decimal) and 4 weekly bills
- Compute **monthly total** and weekly averages
- Show results with and without applying the coupon
- Two implementations:
  - **CLI** (console interaction)
  - **Swing GUI** (simple desktop window)

---

## Requirements
- **Java 11+** (JDK)  
Check with:
```bash
java -version
javac -version
```

---

## Run the Programs

### Swing GUI
Source-file mode:
```bash
java GroceryCalculatorUI.java
```
Or compile then run:
```bash
javac GroceryCalculatorUI.java
java GroceryCalculatorUI
```
A window titled **“Grocery Calculator (Swing)”** will open. Enter coupon + 4 weeks → click **Calculate**.

##  Author
- Kayla Sherwood
