package app.system.cart;

import app.products.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartTest {

    @Mock
    private CartItem laptopItem;
    @Mock
    private Product laptop;

    @InjectMocks
    private Cart cart;

    @Test
    void addToCartAddsItemWhenProductIsNotInCart() {
        // Arrange
        when(laptopItem.getProduct()).thenReturn(laptop);
        when(laptop.getId()).thenReturn(1);

        // Act
        cart.addToCart(laptopItem);

        // Assert
        assertEquals(1, cart.getCartItems().size());
    }
}
