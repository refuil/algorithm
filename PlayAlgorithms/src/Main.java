
import java.util.*;

public class Main{


    public static void main(String[] args) {
        check_bst();

//        check_stack_queue();
//        checkQueue();
//        checkSet();

        String[] words = {"gin", "zen", "gig", "msg","apple"};
        System.out.println("uniqueMorseNum:"+ uniqueMorseRepresentations(words));

//        checkBSTMap();
        int[] nums1 = {1,3,6};
        int[] nums2 = {7,4,1};
        int[] intect = intersectArray(nums1, nums2);
        for(int w: intect) System.out.println(w + ",");

        checkMaxHeap();

        int[] nums = {1,2,1,4,4,5,6,7};
        int k = 3;
//        printList(topKFrequent(nums, k));

        checkSegmentTree();


    }

    private static void check_bst(){
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2,10,9,1,0};
        for(int num: nums){
            bst.add(num);
        }
        //      5
        //    /   \
        //   3    6
        //  / \    \
        // 2  4     8
        // /       /  \
        // 1      9  10
        bst.preOrder();
        System.out.println(bst);

        bst.inOrder();

        System.out.println("=====");
        bst.postOrder();
        System.out.println("=====");

        bst.preOrderNR();
        System.out.println("===== level");
        bst.levelOrder();

        System.out.println("search");
        System.out.println(bst.minimum());

        bst.remove(3);

        System.out.println(bst);
    }

    private static void check_stack_queue(){
        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

    private static double testQueue(Queue<Integer> q, int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i =0; i< opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i= 0; i < opCount; i++)
            q.dequeue();
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    private static void checkQueue(){
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }


    private static void checkSet(){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("data/pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("data/a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }

    //摩尔斯密码是每个字母对应一系列电和短划线，给定一组单词数组，返回所有单词中不同变换的数量。
    public static int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        BSTSet<String> set = new BSTSet<>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString());
        }

//        LinkedListSet<String> set = new LinkedListSet<>();
//        for(String word:  words){
//            StringBuilder res = new StringBuilder();
//            for(int i = 0; i < word.length(); i++){
//                res.append(codes[word.charAt(i) - 'a']);
//            }
//            set.add(res.toString());
//        }
        return set.getSize();
    }

    private static void checkBSTMap(){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("data/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

    //https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
    //350 合并两个数组，求得两个数组重合的元素
    private static int[] intersectArray(int[] nums1, int[] nums2){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums1){
            if(!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int num: nums2){
            if(map.containsKey(num)){
                res.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] ret = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i ++)
            ret[i] = res.get(i);

        return ret;
    }

    private static void checkMaxHeap(){
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }

    private static class Freq implements Comparable<Freq>{

        public int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            if(this.freq < another.freq)
                return 1;
            else if(this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

//    private static List<Integer> topKFrequent(int[] nums, int k){
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        for(int num: nums){
//            if(map.containsKey(num))
//                map.put(num, map.get(num) + 1);
//            else
//                map.put(num, 1);
//        }

//        MaxHeap<Freq> maxHeap = new MaxHeap<>();
//        for(int key: map.keySet()){
//            if(maxHeap.size() < k)
//                maxHeap.add(new Freq(key, map.get(key)));
//            else if(map.get(key) > maxHeap.findMax().freq)
//                maxHeap.replace(new Freq(key, map.get(key)));
//        }
//        LinkedList<Integer> res = new LinkedList<>();
//        while(!maxHeap.isEmpty())
//            res.add(maxHeap.extractMax().e);
//        return res;

//        PriorityQueue<Freq> pq = new PriorityQueue<>();
//        for(int key: map.keySet()){
//            if(pq.getSize() < k)
//                pq.enqueue(new Freq(key, map.get(key)));
//            else if(map.get(key) > pq.getFront().freq){
//                pq.dequeue();
//                pq.enqueue(new Freq(key, map.get(key)));
//            }
//        }
//        LinkedList<Integer> res = new LinkedList<>();
//        while(!pq.isEmpty()){
//            res.add(pq.dequeue().e);
//        }
//        return res;

//    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    //leetcode: range sum Query - Immutable 检索一个数组的某个区间的所有数字之和
    private static void checkSegmentTree(){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
//                new Merger<Integer>() {
//                    @Override
//                    public Integer merge(Integer a, Integer b) {
//                        return a + b;
//                    }
//                });

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2,5));
        System.out.println(segTree.query(0,5));

        segTree.set(2, 10);
        System.out.println(segTree);

        //NumArray2 另一种方法
        SegmentTree seg  = NumArray(nums);
        System.out.println("sumRange: " + sumRange(2,5));

        //NumArray3 数组的方法
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 1 ; i < sum.length ; i ++){
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        System.out.println("sumRange1: " + sumRange1(2,5));

    }

    // sum[i]存储前i个元素和, sum[0] = 0
    // 即sum[i]存储nums[0...i-1]的和
    // sum(i, j) = sum[j + 1] - sum[i]
    private static int[] sum;
    public static int sumRange1(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    private static SegmentTree<Integer> segmentTree;
    private static SegmentTree<Integer> NumArray(Integer[] nums) {

        if(nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
        return segmentTree;
    }

    public static int sumRange(int i, int j) {

        if(segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        return segmentTree.query(i, j);
    }


}