//�ַ���������������

public interface SString
{
    int length();                                //�����ַ����ĳ���
    char charAt(int index);                      //���ش������Ϊindex���ַ�
    SString concat(SString str);                 //���ص�ǰ����str���������ɵ��´�
    SString substring(int begin, int end);       //���ش����ַ���Ŵ�begin��end-1���Ӵ�
    int indexOf(SString pattern);                //����pattern���������еĵ�һ��ƥ��λ��
}
