/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.ultimate.g45682.model;

import atlg4.ultimate.g45682.exception.GameException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Younes Kardillo
 */
public class MyTicTacToeTest {
    

    @Test
    public void testPutLetter_Letter() {
        MyTicTacToe m = new MyTicTacToe();
        m.putLetter(Letters.O, new Point(0,0));
        assertEquals(m.getLetterAt(new Point(0,0)), Letters.O);
    }
    
    @Test
    public void testPutLetter_NoLetter() {
        MyTicTacToe m = new MyTicTacToe();
        m.putLetter(Letters.NONE, new Point(0,0));
    }
    
    @Test(expected = GameException.class)
    public void testPutLetter_NotInside() {
        MyTicTacToe m = new MyTicTacToe();
        m.putLetter(Letters.O, new Point(5,0));
    }
    
    @Test(expected = GameException.class)
    public void testPutLetter_PosNotEmpty() {
        MyTicTacToe m = new MyTicTacToe();
        m.putLetter(Letters.O, new Point(2,0));
        m.putLetter(Letters.X, new Point(2,0));
        
    }
    
        
    
}
