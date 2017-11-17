//字符串抽象数据类型

public interface SString
{
    int length();                                //返回字符串的长度
    char charAt(int index);                      //返回串中序号为index的字符
    SString concat(SString str);                 //返回当前串与str串连接生成的新串
    SString substring(int begin, int end);       //返回串中字符序号从begin至end-1的子串
    int indexOf(SString pattern);                //返回pattern串在主串中的第一次匹配位置
}
