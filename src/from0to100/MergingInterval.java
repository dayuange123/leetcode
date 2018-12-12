package from0to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergingInterval {

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0 || intervals.size() == 1) return intervals;

//        for (int i = 0; i < intervals.size(); i++) {
//            for (int j = 0; j < intervals.size() - i - 1; ++j) {
//                if (intervals.get(j).start > intervals.get(j + 1).start) {
//                    int start = intervals.get(j).start;
//                    int end = intervals.get(j).end;
//                    intervals.set(j, intervals.get(j + 1));
//                    Interval interval = new Interval(start, end);
//                    intervals.set(j + 1, interval);
//                }
//            }
//        }
        intervals.sort((i1, i2) -> i1.start - i2.start);
        List<Interval> re = new ArrayList<>();
        int end = intervals.get(0).end;
        int start = intervals.get(0).start;
        Interval interval = null;
        for (int i = 1; i < intervals.size(); i++) {
            Interval thisIn = intervals.get(i);
            if (thisIn.start > end) {
                interval = new Interval(start, end);
                re.add(interval);
                start = thisIn.start;
                end = thisIn.end;

            } else end = end > thisIn.end ? end : thisIn.end;

        }
        re.add(new Interval(start, end));

        return re;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 3));

        List<Interval> merge = new MergingInterval().merge(intervals);
        merge.forEach(interval -> {
            System.out.println(interval);
        });
    }

}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
