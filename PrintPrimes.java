/**
 * This class calculates a number of primes
 * using a sieve no higher than a set order,
 * and returning no more than a set amount
 * of prime numbers.
 */
public class PrintPrimes {
    int numberOfPrimes;
    int rowsPerPage;
    int columns;
    int maxOrder;
    int listOfPrimes[];
    int listOfNonPrimes[];

    public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columns, int placeholder, int maxOrder) {
        this.numberOfPrimes   = numberOfPrimes;
        this.rowsPerPage  = rowsPerPage;
        this.columns  = columns;
        this.maxOrder = maxOrder;
        this.listOfPrimes = new int[numberOfPrimes];
        this.listOfNonPrimes = new int[maxOrder + 1];
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
        listOfPrimes[0] = 2;
        calculateOddPrimes();
    }

    private boolean isPrime(int order, int val) {
        int n = 2;
        boolean prime;

        prime = true;
        while (n < order && prime) {
            while (listOfNonPrimes[n] < val) {
                listOfNonPrimes[n] = listOfNonPrimes[n] + listOfPrimes[n - 1] + listOfPrimes[n - 1];
            }
            if (listOfNonPrimes[n] == val) {
                prime = false;
            }
            n++;
        }
        return prime;
    }

    /**
     * Helper method that calculates the odd primes using a sieve
     */
    private void calculateOddPrimes() {
        int j = 1;
        int order = 2;
        int square = 9;

        for(int primesFoundSoFar = 1; primesFoundSoFar < numberOfPrimes; primesFoundSoFar++) {
            do {
                j += 2;
                if (j == square) {
                    order++;
                    square = listOfPrimes[order - 1] * listOfPrimes[order - 1];
                    listOfNonPrimes[order - 1] = j;
                }
                
            } while (!isPrime(order,j));
            listOfPrimes[primesFoundSoFar] = j	;
        }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 0;
        while (pageOffset <= numberOfPrimes) {
            System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
            System.out.println("");
            for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++){
                for (int col = 0; col < columns;col++) {
                    if (rowOffset + col * rowsPerPage < numberOfPrimes) {
                        System.out.format("%10d", listOfPrimes[rowOffset + col * rowsPerPage]);
                    }
                }
                System.out.println("");
            }
            System.out.println("\f");
            pageNumber++;
            pageOffset = pageOffset + rowsPerPage * columns;
        }
    }
}

					 
