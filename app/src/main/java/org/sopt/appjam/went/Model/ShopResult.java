package org.sopt.appjam.went.Model;

/**
 * Created by NOEP on 15. 7. 1..
 */
import java.util.List;

/**
 * http://developers.daum.net/services/apis/shopping/search
 *
 * �� ��ũ�� �ִ� ������ ��� ����� �ش��ϴ� �ڷ����� ������!
 *
 * ������ �� ���߾����(������ ����) �Ľ̿� ������ �����ϴ�.
 *
 * List�� ���Ƿ� �����Ѱ��Դϴ�. item�̶�� ������ ������ �˴ϴ�.
 */
public class ShopResult {

    public int result;
    public String title;
    public String sort;
    public String desc;
    public String totalCount;
    public String q;
    public List<ShopItem> item;
    public int pageno;

}
