package LC12;
import java.util.*;

class HashTableOpenHashing {
    private LL[] table;
    private int size;

    public HashTableOpenHashing(int size) {
        this.size = size;
        this.table = new LL[size];
    }

    public void insert(String data) {
        String total;
        int index;
        
        total = computeASCIIString(data);
        index = getIndexFromASCIIString(total);
        index = index % size;

        if (table[index] == null) {
            table[index] = new LL();
        }
        if (table[index].isExists(data)) {
            return;
        }
        table[index].add(new NodeLL(data));
        System.out.println("Kode ASCII dari " + data + " adalah " + total);
        System.out.println("Index dari " + data + " adalah " + index);
    }

    public boolean search(String data) {
        String total = computeASCIIString(data);
        int index = getIndexFromASCIIString(total);
        index = index % size;
        if (table[index] == null) {
            return false;
        }
        return table[index].isExists(data);
    }

    public void delete(String data) {
        String total = computeASCIIString(data);
        int index = getIndexFromASCIIString(total);
        index = index % size;
        if (table[index] == null) {
            return;
        }
        table[index].remove(data);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + " -> ");
            if (table[i] == null) {
                System.out.println("[]");
            } else {
                table[i].print();
            }
        }
    }

    private String computeASCIIString(String data) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            ascii.append((int) data.charAt(i));
        }
        return ascii.toString();
    }

    private int getIndexFromASCIIString(String total) {
        if (total.length() >= 4) {
            int digit2;
            int digit4;
            
            digit2 = Character.getNumericValue(total.charAt(1));
            digit4 = Character.getNumericValue(total.charAt(3));
            
            return digit2 * 10 + digit4;
        } else {
            int intValue = 0;
            try {
                intValue = Integer.parseInt(total);
            } catch (NumberFormatException e) {
                System.out.println("Error converting ASCII string to int: " + e.getMessage());
            }
            return intValue % 100;
        }
    }
}

class NodeLL {
    String data;
    NodeLL next;

    public NodeLL(String data) {
        this.data = data;
        this.next = null;
    }
}

class LL {
    NodeLL head, tail;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(NodeLL input) {
        if (isEmpty()) {
            head = tail = input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    public void remove(String data) {
        NodeLL pointer = head;
        NodeLL prev = null;
        while (pointer != null) {
            if (data.equals(pointer.data)) {
                if (prev == null) {
                    head = pointer.next;
                } else {
                    prev.next = pointer.next;
                }
                size--;
                return;
            }
            prev = pointer;
            pointer = pointer.next;
        }
    }

    public boolean isExists(String data) {
        NodeLL pointer = head;
        while (pointer != null) {
            if (data.equals(pointer.data)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public void print() {
        NodeLL pointer = head;
        System.out.print("[");
        while (pointer != null) {
            System.out.print(pointer.data);
            if (pointer.next != null) {
                System.out.print(", ");
            }
            pointer = pointer.next;
        }
        System.out.println("]");
    }
}

public class LC12_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] sizes;
        int size;
        int n;
        String barisInputKota;
        boolean commandLangsung;
        String firstCommand;
        
        sizes = scanner.nextLine().split(" ");
        size = 0;
        n = 0;

        try {
            size = Integer.parseInt(sizes[0]);
            n = Integer.parseInt(sizes[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format: " + e.getMessage());
            scanner.close();
            return;
        }

        HashTableOpenHashing hashTable = new HashTableOpenHashing(size);

        barisInputKota = scanner.nextLine().trim();
        commandLangsung = false;
        firstCommand = "";

        if (!barisInputKota.isEmpty() && !barisInputKota.startsWith("Cari") && !barisInputKota.startsWith("Hapus") && !barisInputKota.startsWith("Tampilkan")) {
            String[] cities = barisInputKota.split(" ");
            for (String city : cities) {
                hashTable.insert(city);
            }
        } else {
            commandLangsung = true;
            firstCommand = barisInputKota;
        }

        hashTable.printTable();
        System.out.println();

        if (commandLangsung) {
            executeCommand(hashTable, firstCommand);
        }

        for (int i = 0; i < n; i++) {
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                executeCommand(hashTable, command);
            }
        }

        scanner.close();
    }

    private static void executeCommand(HashTableOpenHashing hashTable, String command) {
        if (command.startsWith("Cari")) {
            String[] parts;
            parts = command.split(" ");
            if (parts.length == 2) {
                String city;
                city = parts[1];
                if (hashTable.search(city)) {
                    System.out.println("Kota ada di dalam tabel");
                } else {
                    System.out.println("Kota tidak ada di dalam tabel");
                }
            } else {
                System.out.println("Perintah tidak valid");
            }
        } else if (command.startsWith("Hapus")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                String city;
                city = parts[1];
                hashTable.delete(city);
                System.out.println(city + " berhasil dihapus");
            } else {
                System.out.println("Perintah tidak valid");
            }
        } else if (command.equals("Tampilkan")) {
            hashTable.printTable();
        } else {
            System.out.println("Perintah tidak valid");
        }
    }
}
