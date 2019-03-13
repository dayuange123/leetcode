import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test123 {
    public static void main(String[] args) {
//        ReferenceQueue<String> queue = new ReferenceQueue<>();
//        String s = new String("10");
//        System.out.println(s);
//        PhantomReference<String> sr = new PhantomReference<>(s, queue);
//        System.out.println(s);
//        System.out.println(sr.get());
//        System.gc();                //通知JVM的gc进行垃圾回收
//        System.out.println(sr.get());
//        System.out.println(s);
//        System.out.println(queue.poll());
        Student student=new Student();
        System.out.println(student);
        //student=null;
        System.out.println(student.psr);
        System.gc();
        System.out.println(student);
        System.out.println(student.referenceQueue.poll());
    }
}

class Student {
    PhantomReference<Student> psr ;
    ReferenceQueue<Student> referenceQueue=new ReferenceQueue<>();
    public Student() {


    }

    @Override
    public String toString() {
        return "Student{}";
    }
}



