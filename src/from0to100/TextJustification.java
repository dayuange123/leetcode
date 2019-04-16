package from0to100;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        //每一行 所有单词长度和
        int charNumber = 0;
        int cunrrentIndex = 0;
        int startIndex=0;
        List<String> list = new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        //贪心算法
        while (cunrrentIndex < words.length) {
            //计算一行存放单词数量
            startIndex=cunrrentIndex;
            for (int i = cunrrentIndex; i < words.length; i++) {
                if (charNumber + words[i].length()+cunrrentIndex-startIndex <= maxWidth){
                    charNumber += words[i].length();
                    cunrrentIndex++;
                } else
                    break;
            }
            // end=cunrrentIndex-1
            //将 start-end  的单词进行均匀分布。
            //如果为最后一行
            if(cunrrentIndex==words.length){
                for(int i=startIndex;i<cunrrentIndex;i++){
                    sb.append(words[i]);
                    if(i<cunrrentIndex-1)
                        sb.append(' ');
                }
                int re=maxWidth-sb.length();
                for(int i=0;i<re;i++){
                    sb.append(' ');
                }
            }else {
                //如果此行只有一个单词
                if(cunrrentIndex-startIndex==1){
                    sb.append(words[startIndex]);
                    for(int i=0;i<maxWidth-words[startIndex].length();i++)
                        sb.append(" ");
                }else {
                    int index=(maxWidth-charNumber)%(cunrrentIndex-startIndex-1);
                    for(int i = startIndex;i<cunrrentIndex;i++){
                        sb.append(words[i]);
                        if(i<cunrrentIndex-1){
                            for (int i1 = 0; i1 < (maxWidth-charNumber)/(cunrrentIndex-startIndex-1); i1++) {
                                sb.append(" ");
                            }
                            if(index-->0)
                                sb.append(" ");
                        }
                    }
                }
            }
            list.add(sb.toString());
            charNumber=0;
            sb.setLength(0);
        }
        return list;
    }
    public static void main(String[] args) {
    }
}
