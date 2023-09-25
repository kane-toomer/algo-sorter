import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AlgorithmSorterGUI extends JFrame {
    private JPanel sortingPanel;
    private JButton bubbleSortButton;
    private JButton mergeSortButton;
    private JButton selectionSortButton;
    private JButton insertionSortButton;

    public AlgorithmSorterGUI() {
        setTitle("Visual Algorithm Sorter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sortingPanel = new JPanel();
        sortingPanel.setLayout(null); // Set layout to null for manual positioning

        bubbleSortButton = new JButton("Bubble Sort");
        mergeSortButton = new JButton("Merge Sort");
        selectionSortButton = new JButton("Selection Sort");
        insertionSortButton = new JButton("Insertion Sort");

        bubbleSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSorting("Bubble Sort");
            }
        });

        mergeSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSorting("Merge Sort");
            }
        });

        selectionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSorting("Selection Sort");
            }
        });

        insertionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSorting("Insertion Sort");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(mergeSortButton);
        buttonPanel.add(selectionSortButton);
        buttonPanel.add(insertionSortButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(sortingPanel, BorderLayout.CENTER);
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    updateVisualization(arr); // Update the visualization
                }
            }
        }
    }

    private void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            updateVisualization(arr); // Update the visualization
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // Merge two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            updateVisualization(arr); // Update the visualization
        }
    }

    private void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            updateVisualization(arr); // Update the visualization
        }
    }

    private void startSorting(String algorithm) {
        // Generate 30 random integers between 1 and 100
        int[] dataToSort = new int[30];
        Random random = new Random();

        for (int i = 0; i < dataToSort.length; i++) {
            dataToSort[i] = random.nextInt(100) + 1;
        }

        // Call the selected sorting algorithm based on user choice
        switch (algorithm) {
            case "Bubble Sort":
                bubbleSort(dataToSort);
                break;
            case "Merge Sort":
                mergeSort(dataToSort);
                break;
            case "Selection Sort":
                selectionSort(dataToSort);
                break;
            case "Insertion Sort":
                insertionSort(dataToSort);
                break;
        }
    }

    private void updateVisualization(int[] arr) {
        sortingPanel.removeAll(); // Clear the panel before updating

        // Set the panel layout to null so that you can position elements manually
        sortingPanel.setLayout(null);

        int barWidth = 20; // Width of each bar
        int spacing = 5; // Spacing between bars

        for (int i = 0; i < arr.length; i++) {
            int barHeight = arr[i] * 5; // Adjust the height of the bars as needed
            int xPosition = i * (barWidth + spacing);
            int yPosition = sortingPanel.getHeight() - barHeight;

            JPanel bar = new JPanel();
            bar.setBounds(xPosition, yPosition, barWidth, barHeight);
            bar.setBackground(Color.BLUE); // You can set the color as desired

            sortingPanel.add(bar);
        }

        sortingPanel.revalidate(); // Refresh the panel
        sortingPanel.repaint(); // Repaint the panel to reflect the changes
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmSorterGUI sorterGUI = new AlgorithmSorterGUI();
            sorterGUI.setVisible(true);
        });
    }
}
