import com.sir.wallet.model.Wallet;
import com.sir.wallet.model.WalletController;
import com.sir.wallet.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WalletControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WalletService walletService;

    private WalletController walletController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        walletController = new WalletController(walletService);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    public void testGetWallet() throws Exception {
        long walletId = 1L;
        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        wallet.setBalance(100.0);

        when(walletService.getWalletById(walletId)).thenReturn(wallet);

        mockMvc.perform(get("/api/wallet/wallets/{id}", walletId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(walletId))
                .andExpect(jsonPath("$.balance").value(100.0));

        verify(walletService, times(1)).getWalletById(walletId);
        verifyNoMoreInteractions(walletService);
    }
    @Test
    public void testUpdateWallet() throws Exception {
        long walletId = 1L;
        Wallet updatedWallet = new Wallet();
        updatedWallet.setId(walletId);
        updatedWallet.setBalance(200.0);

        when(walletService.updateWallet(eq(walletId), any(Wallet.class))).thenReturn(updatedWallet);

        mockMvc.perform(put("/api/wallet/wallets/{id}", walletId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"balance\": 200.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(walletId))
                .andExpect(jsonPath("$.balance").value(200.0));

        verify(walletService, times(1)).updateWallet(eq(walletId), any(Wallet.class));
        verifyNoMoreInteractions(walletService);
    }
}
