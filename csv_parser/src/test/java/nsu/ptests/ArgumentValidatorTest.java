package nsu.ptests;

import nsu.parser.io.*;
import nsu.parser.*;
import nsu.parser.exceptions.*;
import nsu.parser.validators.ArgumentValidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArgumentValidatorTest {
    
    @Test
    void testCountArgs() {
        assertThrows(InvalidCountArgsException.class, () -> ArgumentValidator.validate(null));
        assertThrows(InvalidCountArgsException.class, () -> ArgumentValidator.validate(new String[]{"why", "hello", "lol"}));
    }
}