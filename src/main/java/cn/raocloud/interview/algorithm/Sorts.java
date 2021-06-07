package cn.raocloud.interview.algorithm;

import java.util.Arrays;

public class Sorts {

    public static void printf(int[] itemList) {
        System.out.print("[");
        for (int index = 0; index < itemList.length; index++) {
            System.out.print(itemList[index]);
            if (index != itemList.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] itemList = {3, 1, 6, 2, 23, 45, 10, 4};
        printf(bubbleSort(Arrays.copyOf(itemList, itemList.length)));
        printf(selectionSort(Arrays.copyOf(itemList, itemList.length)));
        printf(insertionSort(Arrays.copyOf(itemList, itemList.length)));
        printf(mergeSort(Arrays.copyOf(itemList, itemList.length)));
        printf(radixSort(Arrays.copyOf(itemList, itemList.length)));
        printf(countingSort(Arrays.copyOf(itemList, itemList.length)));
        printf(heapSort(Arrays.copyOf(itemList, itemList.length)));
    }

    /**
     算法描述：
         1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
         2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
         3、针对所有的元素重复以上的步骤，除了最后一个；
         4、重复步骤1~3，直到排序完成。
     稳定性：
        在相邻元素相等时，它们并不会交换位置，所以，冒泡排序是稳定排序
     适用场景：
        冒泡排序思路简单，代码也简单，特别适合小数据的排序。但是，由于算法复杂度较高，在数据量大的时候不适合使用
     时间复杂度：
        最好时间复杂度：O(n), 最坏时间复杂度：O(n^2), 平均时间复杂度：O(n^2)
     *
     * @param itemList 待排序数组
     * @return 排序好数组
     */
    public static int[] bubbleSort(int[] itemList) {
        // 记录第一次比较是否发生数据交换，如果没有交换表明数据完全有序
        // 不需要再进行遍历比较并交换
        boolean isSwap = false;
        for (int i = 0; i < itemList.length; i++) {
            for (int j = 0; j < itemList.length - 1 - i; j++) {
                if (itemList[j] > itemList[j + 1]) {
                    int temp = itemList[j + 1];
                    itemList[j + 1] = itemList[j];
                    itemList[j] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) { break; }
        }

        return itemList;
    }

    /**
     算法描述：
         1、在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
         2、从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
         3、重复第二步，直到所有元素均排序完毕。
     稳定性：
        用数组实现的选择排序是不稳定的，用链表实现的选择排序是稳定的。
        一般提到排序算法时，大家往往会默认是数组实现，所以选择排序是不稳定的。
     适用场景：
        选择排序实现也比较简单，并且由于在各种情况下复杂度波动小，因此一般是优于冒泡排序的。
        在所有的完全交换排序中，选择排序也是比较不错的一种算法。
        但是，由于固有的O(n^2)复杂度，选择排序在海量数据面前显得力不从心。
        因此，它适用于简单数据排序。
     时间复杂度：
        最好时间复杂度：O(n^2), 最坏时间复杂度：O(n^2), 平均时间复杂度：O(n^2)
     *
     * @param itemList 待排序列表
     * @return 已排序列表
     */
    public static int[] selectionSort(int[] itemList) {

        for (int currentIndex = 0; currentIndex < itemList.length; currentIndex++) {
            int minIndex = currentIndex;
            for (int selectionIndex = currentIndex + 1; selectionIndex < itemList.length; selectionIndex++) {
                if (itemList[minIndex] > itemList[selectionIndex]) {
                    minIndex = selectionIndex;
                }
            }
            int temp = itemList[minIndex];
            itemList[minIndex] = itemList[currentIndex];
            itemList[currentIndex] = temp;
        }

        return itemList;
    }

    /**
     算法描述：
         把待排序的数组分成已排序和未排序两部分，初始的时候把第一个元素认为是已排好序的。
         从第二个元素开始，在已排好序的子数组中寻找到该元素合适的位置并插入该位置。
         重复上述过程直到最后一个元素被插入有序子数组中。
     稳定性：
        由于只需要找到不大于当前数的位置而并不需要交换，因此，直接插入排序是稳定的排序方法。
     适用场景：
        插入排序由于O(n^2)的复杂度，在数组较大的时候不适用。但是，在数据比较少的时候，
        是一个不错的选择，一般做为快速排序的扩充。例如，在STL的sort算法和stdlib的qsort算法中，
        都将插入排序作为快速排序的补充，用于少量元素的排序。又如，在JDK 7 java.util.Arrays所
        用的sort方法的实现中，当待排数组长度小于47时，会使用插入排序。
     *
     * @param itemList 待排序数组
     * @return 已经排好序的数组
     */
    public static int[] insertionSort(int[] itemList) {

        for (int currentIndex = 1; currentIndex < itemList.length; currentIndex++) {
            int currentItem = itemList[currentIndex];
            int preIndex = currentIndex - 1;
            while (preIndex >= 0 && itemList[preIndex] > currentItem) {
                itemList[preIndex + 1] = itemList[preIndex];
                preIndex--;
            }
            itemList[preIndex + 1] = currentItem;
        }
        return itemList;
    }

    /**
     概述：
        归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
        将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     算法描述：
         申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
         设定两个指针，最初位置分别为两个已经排序序列的起始位置
         比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
         重复步骤3直到某一指针到达序列尾
         将另一序列剩下的所有元素直接复制到合并序列尾
     算法分析：
        归并排序是一种稳定的排序方法。和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
        因为始终都是O(nlogn）的时间复杂度。代价是需要额外的内存空间
     * @param itemList 待排序数组
     * @return 已排序数组
     */
    public static int[] mergeSort(int[] itemList) {
        if (itemList.length < 2) {
            return itemList;
        }
        int middle = itemList.length / 2;
        int[] leftList = Arrays.copyOfRange(itemList, 0, middle);
        int[] rightList = Arrays.copyOfRange(itemList, middle, itemList.length);
        return merge(mergeSort(leftList), mergeSort(rightList));
    }

    private static int[] merge(int[] leftList, int[] rightList) {
        int[] result = new int[leftList.length + rightList.length];
        int count = 0;
        int leftCount = 0, rightCount = 0;
        while (leftCount < leftList.length && rightCount < rightList.length) {
            if (leftList[leftCount] < rightList[rightCount]) {
                result[count] = leftList[leftCount];
                leftCount++;
            } else {
                result[count] = rightList[rightCount];
                rightCount++;
            }
            count++;
        }

        while (leftCount < leftList.length) {
            result[count] = leftList[leftCount];
            leftCount++;
            count++;
        }

        while (rightCount < rightList.length) {
            result[count] = rightList[rightCount];
            rightCount++;
            count++;
        }

        return result;
    }

    /**
     概述：
        计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
        作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数
     算法描述：
        找出待排序的数组中最大和最小的元素；
        统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
        对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
        反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
     算法分析：
        计数排序是一个稳定的排序算法。
        当输入的元素是n个0到k之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，
        其排序速度快于任何比较排序算法。当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
     *
     * @param itemList 待排序数组
     * @return 已排序数组
     */
    public static int[] countingSort(int[] itemList) {
        int minItem = itemList[0], maxItem = itemList[0];
        for (int index = 0; index < itemList.length; index++) {
            minItem = Math.min(minItem, itemList[index]);
            maxItem = Math.max(maxItem, itemList[index]);
        }

        int[] counter = new int[maxItem - minItem + 1];
        for (int index = 0; index < itemList.length; index++) {
            counter[itemList[index] - minItem]++;
        }

        for (int sortIndex = 0, index = 0; index < counter.length; index++) {
            if (counter[index] != 0) {
                itemList[sortIndex] = minItem + index;
                sortIndex++;
            }
        }
        return itemList;
    }

    /**
     算法描述：
        取得数组中的最大数，并取得位数；
        arr为原始数组，从最低位开始取每个位组成radix数组；
        对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     稳定性：
        每一轮映射和收集操作，都保持从左到右的顺序进行，如果出现相同的元素，则保持他们在原始数组中的顺序，所有技术排序是一种稳定性的排序
     适用场景：
        基数排序要求较高，元素必须是整数，整数时长度10W以上，最大值100W以下效率较好，但是基数排序比其他排序好在可以适用字符串，或者其他需要根据多个
        条件进行排序的场景，例如日期，先排序日，再排序月，最后排序年 ，其它排序算法可是做不了的

     *
     * @param itemList 待排序的数组
     * @return 已排序数组
     */
    public static int[] radixSort(int[] itemList) {
        int radix = 10, divisor = 1, round = 1;
        int distance = getDistance(itemList);

        int[][] bucket = new int[radix][itemList.length];
        while (round <= distance) {
            int[] counter = new int[radix];
            for (int index = 0; index < itemList.length; index++) {
                int which = (itemList[index] / divisor) % radix;
                bucket[which][counter[which]] = itemList[index];
                counter[which]++;
            }

            for (int index = 0, i = 0; i < radix; i++) {
                if (counter[i] != 0) {
                    for (int j = 0; j < counter[i]; j++) {
                        itemList[index] = bucket[i][j];
                        index++;
                    }
                    counter[i] = 0;
                }
            }
            divisor *= 10;
            round++;
        }
        return itemList;
    }

    private static int getDistance(int[] itemList) {
        int maxItem = getMax(itemList);
        int digits = 1;
        while ((maxItem = maxItem / 10) != 0) {
            digits++;
        }
        return digits;
    }

    private static int getMax(int[] itemList) {
        int maxItem = itemList[0];
        for (int index = 1; index < itemList.length; index++) {
            if (itemList[index] > maxItem) {
                maxItem = itemList[index];
            }
        }
        return maxItem;
    }

    public static int[] heapSort(int[] itemList) {
        //1.构建大顶堆
        for(int i = itemList.length / 2 - 1; i >= 0; i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(itemList, i, itemList.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j = itemList.length - 1; j > 0; j--){
            swap(itemList,0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(itemList,0, j);//重新对堆进行调整
        }
        return itemList;
    }

    private static void adjustHeap(int[] itemList, int i, int length) {
        int temp = itemList[i];//先取出当前元素i
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k + 1 < length && itemList[k] < itemList[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(itemList[k] > temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                itemList[i] = itemList[k];
                i = k;
            }else{
                break;
            }
        }
        itemList[i] = temp;//将temp值放到最终的位置
    }

    private static void swap(int[] arr,int a ,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}