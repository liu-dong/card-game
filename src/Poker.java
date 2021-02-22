/**
 * @author LD
 */
public class Poker {
    private int sort;
    //A,2,3,4,5,6,7,8,9,10,J,Q,K，王
    private String number;//数字
    //spade：黑桃,heart：红心,club：梅花,diamond：方块,red：大王,black：小王
    private String flowerColor;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public Poker(int sort, String number, String flowerColor) {
        this.sort = sort;
        this.number = number;
        this.flowerColor = flowerColor;
    }

    public Poker() {
    }

    @Override
    public String toString() {
//        return "{" + "花色：" + flowerColor + ", 数字：" + number + "}\r\n";
        return "{" + sort+"：" + flowerColor + "—" + number + "}\r\n";
    }
}
