public class TopSecret {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Listing available files...");
            // TODO: call file listing method
            return;
        }

        if (args.length == 1) {
            int fileNumber;

            try {
                fileNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error: fileNumber must be an integer.");
                return;
            }

            System.out.println("Deciphering file " + fileNumber + " using default key...");
            // TODO: call default decrypt method
            return;
        }

        if (args.length == 2) {
            int fileNumber;
            String keyFile = args[1];

            try {
                fileNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error: fileNumber must be an integer.");
                return;
            }

            System.out.println("Deciphering file " + fileNumber + " using key file: " + keyFile);
            // TODO: call decrypt with key method
            return;
        }

        System.out.println("Usage:");
        System.out.println("java TopSecret");
        System.out.println("java TopSecret <fileNumber>");
        System.out.println("java TopSecret <fileNumber> <keyFile>");
    }
}