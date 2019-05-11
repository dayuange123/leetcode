import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test123 {
    public static void main(String[] args) {
       Student s=new Student();
       Student s1=new Student();
       Student s2=new Student();

    }
}

class Student {
    PhantomReference<Student> psr ;
    ReferenceQueue<Student> referenceQueue=new ReferenceQueue<>();
    static {
        System.out.println("i an student");
    }
    public Student() {


    }

    @Override
    public String toString() {
        return "Student{}";
    }
}



