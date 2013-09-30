public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage;
  int columns;
  int WW;
  int maxOrder;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columns, int WW, int maxOrder) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columns  = columns;
    this.WW  = WW;
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
      boolean JPRIME;
      int N;
      int MULT[] = new int[maxOrder + 1];

      int J = 1;
      int order = 2;
      int square = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          J = J + 2;
          if (J == square) {
            order = order + 1;
            square = listOfPrimes[order] * listOfPrimes[order];
            MULT[order - 1] = J;
          }
          N = 2;
          JPRIME = true;
          while (N < order && JPRIME) {
            while (MULT[N] < J)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == J)
              JPRIME = false;
            N = N + 1;
          }
        } while (!JPRIME);
        listOfPrimes[primesFoundSoFar] = J;
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

					 
