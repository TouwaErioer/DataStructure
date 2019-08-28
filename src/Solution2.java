public class Solution2 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        while(prev.next != null){
            if(prev.next.val == val){
                ListNode cur = prev.next;
                prev.next = cur.next;
                cur = null;
            }else{
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }
}
