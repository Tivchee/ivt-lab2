package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTs;
  private TorpedoStore mockTs2;

  @BeforeEach
  public void init(){
    mockTs=mock(TorpedoStore.class);
    mockTs2=mock(TorpedoStore.class);
    
    this.ship = new GT4500(mockTs,mockTs2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTs.fire(1)).thenReturn(true);
    when(mockTs2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTs,times(1)).fire(1);
    verify(mockTs2,times(0)).fire(1);
  
  }
  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
    when(mockTs.fire(1)).thenReturn(false);
    when(mockTs2.fire(1)).thenReturn(false);
    when(mockTs.isEmpty()).thenReturn(true);
    when(mockTs2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTs,times(0)).fire(1);
    verify(mockTs2,times(0)).fire(1);
  
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTs.getTorpedoCount()).thenReturn(100);
    when(mockTs2.getTorpedoCount()).thenReturn(100);
    when(mockTs.fire(100)).thenReturn(true);
    when(mockTs2.fire(100)).thenReturn(true);
    when(mockTs.isEmpty()).thenReturn(false);
    when(mockTs2.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    
    verify(mockTs,times(1)).fire(100);
    verify(mockTs2,times(1)).fire(100);
  }

}
