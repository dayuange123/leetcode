package from0to100;

public class WildcardMatching {

    /**
     * 给定输入字符串（s）和模式（p），实现通配符模式匹配和支持'?'和'*'。
     * <p>
     * '？' 匹配任何单个字符。
     * '*'匹配任何字符序列（包括空序列）。
     * 匹配应覆盖整个输入字符串（非部分）。
     * <p>
     * 注意：
     * <p>
     * s 可能是空的，只包含小写字母a-z。
     * p可能是空的，只包含小写字母a-z和字符，如? 或  *。
     * 例1：
     * <p>
     * 输入：
     * s =“aa”
     * p =“a” 输出： false
     * 说明： “a”与整个字符串“aa”不匹配。
     */

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatch("aa", "*"));
    }

    public boolean isMatch(String s, String p) {
        int scur = 0, sstar = 0;
        int pcur = 0, pstar = -1;  //代表指针、

        while (scur < s.length()) {

            if (pcur < p.length() && (s.charAt(scur) == p.charAt(pcur) || p.charAt(pcur) == '?')) {
                scur++;
                pcur++;
            } else if (pcur < p.length() && p.charAt(pcur) == '*') {
                sstar = scur;    //让star 记录下该位置 以保证匹配失败的回溯
                pstar = pcur++;  //让pstar记录当前 位置 也就是*  然后 pcur +1
            } else if (pstar != -1) {
                //这里就是 上面两个都不成立 也就是 匹配失败 而且 pstar 所记录的位置有效(不为初始值-1) 那么回溯
                //这就是我们为什么开始给他-1  因为如果没有给其赋值  就没法回溯直接return false ，
                //然后继续重新从 *后面的元素开始匹配，只不过这次多匹配一个s中的字符，因为上一次匹配不成功。
                //所以sstar +1  并且让scur=sstar
                pcur = pstar + 1;
                scur = ++sstar;
            } else {
                //如果上面都不成立 那么失败
                return false;
            }
        }
        //如果匹配完毕，也就是s通过p的字符匹配完毕了。但是 p的字符不一定完了
        if (pcur == p.length()) return true;
        while (p.charAt(pcur) == '*') {
            if (++pcur == p.length())
                return true;
        }
        return false;
    }

}