package berry.justin.spreadsheet.calculator.expression;

import berry.justin.spreadsheet.calculator.action.Action;
import berry.justin.spreadsheet.calculator.action.ActionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SymbolProcessorTest {

  private SymbolProcessor symbolProcessor;
  private ActionFactory actionFactoryMock;
  private Stack<Double> operands;
  private ArrayList<String[]> spreadsheet;
  private Action actionMock;

  @Before
  public void setUp() {
    operands = new Stack<Double>() { {
      push(1.0);
      push(2.0);
      push(3.0);
    } };
    spreadsheet = new ArrayList<>();

    actionFactoryMock = mock(ActionFactory.class);
    actionMock = mock(Action.class);
    when(actionFactoryMock.fromSymbol(eq(spreadsheet), anyString()))
        .thenReturn(actionMock);
    symbolProcessor = new SymbolProcessor(actionFactoryMock);
  }

  @Test
  public void delegatesToActionFactoryForNextAction() {
    symbolProcessor.process(spreadsheet, operands, "+");
    verify(actionFactoryMock).fromSymbol(spreadsheet, "+");
  }

  @Test
  public void appliesActionWithCorrectNumberOfOperands() {
    when(actionMock.numberOfOperands()).thenReturn(3);
    symbolProcessor.process(spreadsheet, operands, "+");

    ArgumentCaptor<Double> captor = forClass(Double.class);
    verify(actionMock).apply(captor.capture());

    List<Double> capturedValues = captor.getAllValues();
    assertThat(capturedValues.get(0), is(1.0));
    assertThat(capturedValues.get(1), is(2.0));
    assertThat(capturedValues.get(2), is(3.0));
  }

  @Test
  public void returnsStackWithNewResult() {
    when(actionMock.numberOfOperands()).thenReturn(2);
    when(actionMock.apply(ArgumentMatchers.<Double[]>any())).thenReturn(99.0);
    Stack<Double> result = symbolProcessor.process(spreadsheet, operands, "+");
    assertThat(result.size(), is(operands.size() - 1));
    assertThat(result.pop(), is(99.0));
    assertThat(result.pop(), is(1.0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsErrorIfOperatorDemandExhaustsAvailableOperands() {
    when(actionMock.numberOfOperands()).thenReturn(10);
    symbolProcessor.process(spreadsheet, operands, "+");
  }

  @Test
  public void returnsSingleton() {
    SymbolProcessor instance = SymbolProcessor.getInstance();
    assertThat(SymbolProcessor.getInstance(), sameInstance(instance));
  }
}
