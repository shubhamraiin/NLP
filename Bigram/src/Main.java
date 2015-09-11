/*Assignment 2 
 * 
 * This is the main class of the assignment. 
 * This class basically constructs the table for different bigram probabilities of the Two sentences provided.
 */

public class Main
{
    
    public static final String sent1 = "The company chairman said he will increase the profit next year .";
    public static final String sent2 = "The president said he believes the last year profit were good .";

    private BigramModel model;

    public void showCount(String input)
    {
        Object[][] countTable = model.count(input);
        System.out.println("Bigram Counts");
        showTable(countTable);
    }

    public void withoutSmooth(String input)
    {
        Object[][] fillTable = model.withoutSmoothing(input);
        System.out.println("\n--> Without Smoothing");
        showTable(fillTable);

        System.out.println("\nTotal probability: "
                + model.getTotal(fillTable,
                        input.toLowerCase().split("\\ +")) + '\n');
    }

    public void addOneSmooth(String input)
    {
        Object[][] fillTable = model.addoneSmoothing(input);
        System.out.println("--> Add-One Smoothing");
        showTable(fillTable);

        System.out.println("\nTotal probability: "
                + model.getTotal(fillTable,
                        input.toLowerCase().split("\\ +")) + '\n');
    }

    public void goodturingSmooth(String input)
    {
        Object[][] fillTable = model.goodturingSmoothing(input);
        System.out.println("--> Good-Turing Smoothing");
        showTable(fillTable);

        System.out.println("\nTotal probability: "
                + model.getTotal(fillTable,
                        input.toLowerCase().split("\\ +")) + '\n');
    }

    private void showTable(Object[][] table)
    {
        for (Object[] row : table)
        {
            for (Object elem : row)
            {
                if (null == elem)
                {
                    System.out.printf("%10s", "");
                    continue;
                }

                if (elem instanceof String)
                {
                    System.out.printf("%10s", elem);
                }
                if (elem instanceof Integer)
                {
                    System.out.printf("%10d", elem);
                }
                if (elem instanceof Double)
                {
                    if (((Double) elem).doubleValue() == 0.0)
                    {
                        System.out.printf("%10s", '0');
                    }
                    else
                    {
                        System.out.printf("%10f", elem);
                    }
                }
            }
            System.out.println();
        }
    }

    public void setModel(BigramModel model)
    {
        this.model = model;
    }

    public static void main(String[] args) 
    {
        if (args.length != 1)
        {
            System.out.print("Type: Main sent1 \n   or: Main sent2  \n   or: Main \"Any Sentence\", (ensure that the sentence is enclosed within \" \")\n");
            return;
        }

        String targetStr = "";
        if (args[0].equals("sent1"))
        {
            targetStr = sent1;
            System.out.println("Sentence 1: The company chairman said he will increase the profit next year. ");

        }
        else if (args[0].equals("sent2"))
        {
            targetStr = sent2;
            System.out.println("Sentence 2: The president said he believes the last year profit were good. ");

        }
        else if (1 == args[0].split("\\ +").length)
        {
            System.err.println("Bigram ony.");
            return;
        }
        else
        {
            targetStr = args[0];
        }

        BigramModel mod = new BigramModel();
        mod.trainModel();

        Main var = new Main();
        var.setModel(mod);
        var.showCount(targetStr);
        var.withoutSmooth(targetStr);
        var.addOneSmooth(targetStr);
        var.goodturingSmooth(targetStr);
       
        if (!Read.isFileExisted("bigram"))
        {
            Read.writeFile("bigram", mod.toString());
        }
    }
}