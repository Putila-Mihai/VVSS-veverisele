package inventory;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository mockRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test for error when Min > Max")
    @Tag("Error")
    @Test
    void testMinGreaterThanMax() {
        String name = "Piulita";
        String price = "35";
        String inventory = "2";
        String min = "10";
        String max = "30";

        doNothing().when(mockRepository).addPart(any(Part.class));

        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "10");

        assertTrue(errorMessage.contains("Inventory level is lower than minimum value. "));
    }

    // @DisplayName adnotare pentru a oferi un nume descriptiv testului
    @DisplayName("Test for successful part addition")
    @Tag("Good")
    @Test
    void testPartAddedSuccessfully() {

        String name = "surub";
        String price = "5.5";
        String inventory = "10";
        String min = "5";
        String max = "1000";


        doNothing().when(mockRepository).addPart(any(Part.class));

        // Act
        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "5");

        assertNull(errorMessage);
    }

    @Tag("Error")
    @Test
    void testAddPartWithPriceNegative() {
        String name = "balama";
        String price = "-5";
        String inventory = "5";
        String min = "1";
        String max = "100";

        // Mock repository call
        doNothing().when(mockRepository).addPart(any(Part.class));

        // Act
        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "10");

        assertTrue(errorMessage.contains("The price must be greater than 0. "));
    }

    @Tag("Good")
    @Test
    void testAddPartSmoke() {
        String name = "Rezistenta";
        String price = "3";
        String inventory = "10";
        String min = "10";
        String max = "1000";

        // Mock repository call
        doNothing().when(mockRepository).addPart(any(Part.class));

        // Act
        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "10");

        assertNull(errorMessage); // Record added
        verify(mockRepository, times(1)).addPart(any(Part.class)); // Verify addPart is called once
    }

    @Tag("TC_BVA_GOOD")
    @ParameterizedTest
    @CsvSource({
            "A, 0.01, 1, 1, 15,",
            "Screw, 5.99, 10, 5, 15,"
    })
    @DisplayName("Test multiple parts with different parameters")
    void testMultipleParts(String name, double price, int inventory, int min, int max, String expectedErrorMessage) {
        String errorMessage = inventoryService.validateAndAddPart(name, String.valueOf(price), String.valueOf(inventory), String.valueOf(min), String.valueOf(max), false, "5");

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Tag("TC_BVA_ERROR")
    @Test
    void testAddPartWithNameEmpty() {
        String name = "";
        String price = "0.01";
        String inventory = "1";
        String min = "10";
        String max = "15";

        // Mock repository call
        doNothing().when(mockRepository).addPart(any(Part.class));

        // Act
        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "10");

        assertTrue(errorMessage.contains("A name has not been entered. "));
    }

    @Tag("TC_BVA_ERROR")
    @Test
    void testAddPartWithPriceZero() {
        String name = "Bolt";
        String price = "0";
        String inventory = "5";
        String min = "10";
        String max = "15";

        // Mock repository call
        doNothing().when(mockRepository).addPart(any(Part.class));

        // Act
        String errorMessage = inventoryService.validateAndAddPart(name, price, inventory, min, max, false, "10");

        assertTrue(errorMessage.contains("The price must be greater than 0. "));
    }

    @Disabled("Test is temporarily disabled due to bug in part price validation")
    @Test
    void testDisabledTest() {

        assertEquals(1, 1);
    }
}
