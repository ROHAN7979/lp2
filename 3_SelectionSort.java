import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter size of Array");
        int n = in.nextInt();

        int nums[] = new int[n]; // Fix: Use 'n' as the size of the array

        System.out.println("Enter elements in array:");
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        System.out.println("Entered Array is :");
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }

        for (int i = 0; i < n - 1; i++) { // Fix: Change the condition to 'n - 1'
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            int temp = nums[min];
            nums[min] = nums[i];
            nums[i] = temp;
        }

        System.out.println("\nSorted Array after selection Sort is : "); // Add a new line character
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

// //It iterates through the array from the first element to the last element.
// For each iteration, it finds the index of the minimum element in the remaining unsorted portion of the array.
// If a smaller element is found, it updates the minimum index.
// After completing the inner loop, it swaps the minimum element with the current element if necessary.
// This process is repeated until the entire array is sorted.