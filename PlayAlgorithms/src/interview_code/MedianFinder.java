package interview_code;

import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> left;  //大根堆
    PriorityQueue<Integer> right; //小根堆

    /** initialize your data structure here. */
    public MedianFinder() {
        left = new PriorityQueue<>((n1, n2) -> n2-n1);
        right = new PriorityQueue<>();
    }

    //1. 保证数据平均分配到两个堆中（两个堆中的数目之差不能超过1）
    //2. 在数目总数为偶数时，新数据插入最小堆，否则插入最大堆。
    //3. 保证最大堆的数据大于最小堆的数据。
    public void addNum(int num) {
        left.add(num);
        right.add(left.poll());
        if(left.size() + 1 < right.size())
            left.add(right.poll());
    }

    public double findMedian() {
        if(right.size() > left.size())
            return right.peek();
        return (double)(left.peek() + right.peek())/2;
    }
}