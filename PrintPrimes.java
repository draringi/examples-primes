public class PrintPrimes {
    int numberOfPrimes;
    int rowsPerPage;
    int columns;
    int maxOrder;
    int listOfPrimes[];

    public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columns, int placeholder, int maxOrder) {
        this.numberOfPrimes   = numberOfPrimes;
        this.rowsPerPage  = rowsPerPage;
        this.columns  = columns;
        this.maxOrder = maxOrder;
        this.listOfPrimes = new int[numberOfPrimes + 1];
    }


    public static void main(String[] args) {
        PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
        printPrimes.calculatePrimes();
        printPrimes.printPrimes();
    }

    public void calculatePrimes() {
        /* Two is the only even prime. All other prime numbers are odd.
         * To simplify the code, we simply add 2 as a prime number, and
         * delegate the task of finding all odd prime numbers to a helper
         * function.
         */
        listOfPrimes[1] = 2;
        calculateOddPrimes();
    }

    private void calculateOddPrimes() {
        boolean jPrime;
        int n;
        int mult[] = new int[maxOrder + 1];
  
        int j = 1;
        int order = 2;
        int square = 9;

        for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
            do {
                j += 2;
                if (j == square) {
                    order++;
                    square = listOfPrimes[order] * listOfPrimes[order];
                    mult[order - 1] = j;
                }
                n = 2;
                jPrime = true;
                while (n < order && jPrime) {
                    while (mult[n] < j) 
                        mult[n] = mult[n] + listOfPrimes[n] + listOfPrimes[n];
                    if (mult[n] == j)
                        jPrime = false;
                    n++;
                }
            } while (!jPrime);
            listOfPrimes[primesFoundSoFar] = j	;
        }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
            System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
            System.out.println("");
            for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++){
                for (int col = 0; col < columns;col++)
                    if (rowOffset + col * rowsPerPage <= numberOfPrimes)
                        System.out.format("%10d", listOfPrimes[rowOffset + col * rowsPerPage]);
                System.out.println("");
            }
            System.out.println("\f");
            pageNumber++;
            pageOffset = pageOffset + rowsPerPage * columns;
        }
    }
}

					 
