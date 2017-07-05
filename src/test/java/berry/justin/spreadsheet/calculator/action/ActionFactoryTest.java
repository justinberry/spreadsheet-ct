package berry.justin.spreadsheet.calculator.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsSame.sameInstance;

@RunWith(JUnit4.class)
public class ActionFactoryTest {

  private ActionFactory actionFactory;

  @Before
  public void setup() {
    actionFactory = ActionFactory.getInstance();
  }

  @Test
  public void buildsAdditionAction() {
    assertThat(actionFactory.fromSymbol(null, "+"),
        instanceOf(AdditionAction.class));
  }

  @Test
  public void buildsSubtractionAction() {
    assertThat(actionFactory.fromSymbol(null, "-"),
        instanceOf(SubtractionAction.class));
  }

  @Test
  public void buildsMultiplicationAction() {
    assertThat(actionFactory.fromSymbol(null, "*"),
        instanceOf(MultiplicationAction.class));
  }

  @Test
  public void buildsReferenceAction() {
    assertThat(actionFactory.fromSymbol(null, "A10"),
        instanceOf(ReferenceAction.class));
  }

  @Test
  public void buildsReferenceActionWithLowercaseLetters() {
    assertThat(actionFactory.fromSymbol(null, "a10"),
        instanceOf(ReferenceAction.class));
  }

  @Test
  public void buildsDivisionAction() {
    assertThat(actionFactory.fromSymbol(null, "/"),
        instanceOf(DivisionAction.class));
  }

  @Test
  public void buildsReturnValueAction() {
    assertThat(actionFactory.fromSymbol(null, "99"),
        instanceOf(ReturnValueAction.class));
  }

  @Test
  public void returnValueActionContainsValue() {
    assertThat(actionFactory.fromSymbol(null, "99").apply(),
        equalTo(99.0));
  }

  @Test
  public void returnsSingleton() {
    ActionFactory instance = ActionFactory.getInstance();
    assertThat(ActionFactory.getInstance(), sameInstance(instance));
  }
}
