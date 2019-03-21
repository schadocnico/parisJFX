package facade;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class FacadeParisStaticImplTest extends FacadeParisTest {
    public FacadeParisStaticImplTest() {
        specialDedicaceYo( () -> new FacadeParisStaticImpl() );
    }
}
