package from0to100;

import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int index = 0;
        StringBuilder s = new StringBuilder();
        retry:
        while (index < path.length()) {
            s.setLength(0);
            while (path.charAt(index) == '/') {
                index++;
                if (index == path.length())
                    break retry;
            }
            int n = 0;
            if (path.charAt(index) == '.') {
                while (path.charAt(index) == '.') {
                    n++;
                    s.append(path.charAt(index));
                    index++;
                    if (index == path.length()) {
                        if (!stack.isEmpty() && n == 2) stack.pop();
                        if (n > 2) stack.add(s.toString());
                        break retry;
                    }
                }
                if (n <= 2 && path.charAt(index) == '/') {
                    if (!stack.isEmpty() && n == 2)
                        stack.pop();
                    continue;
                }
            }
            while (path.charAt(index) != '/') {
                s.append(path.charAt(index));
                index++;
                if (index == path.length()) {
                    stack.add(s.toString());
                    break retry;
                }
            }
            stack.add(s.toString());
        }
        s.setLength(0);
        for (Object object : stack.toArray()) {
            s.append('/');
            s.append(object);
        }
        return s.toString().length() == 0 ? "/" : s.toString();
    }
    public static void main(String[] args) {
        System.out.println(new SimplifyPath().simplifyPath("/a/./b/../../c/"));

    }
}
