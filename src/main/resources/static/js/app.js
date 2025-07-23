document.addEventListener('DOMContentLoaded', () => {
    const productList = document.getElementById('product-list');
    const customerSelector = document.getElementById('customerSelector');
    const cartItemsContainer = document.getElementById('cart-items');
    const cartCount = document.getElementById('cart-count');
    const clearCartBtn = document.getElementById('clear-cart-btn');

    const API_BASE_URL = 'http://localhost:8080';
    let selectedCustomerId = null;

    const loadCustomers = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/customers`);
            const customers = await response.json();
            customerSelector.innerHTML = customers.map(c => `<option value="${c.id}">${c.name}</option>`).join('');
            selectedCustomerId = customerSelector.value;
            loadCart();
        } catch (error) {
            console.error('Error loading customers:', error);
        }
    };

    const loadProducts = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/products`);
            const products = await response.json();
            productList.innerHTML = products.map(product => `
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">$${product.price.toFixed(2)}</p>
                            <button class="btn btn-success add-to-cart-btn" data-product-id="${product.id}">Add to Cart</button>
                        </div>
                    </div>
                </div>
            `).join('');
        } catch (error) {
            console.error('Error loading products:', error);
        }
    };

    const updateCartUI = (cart) => {
        const items = cart?.items || [];
        cartCount.textContent = items.reduce((sum, item) => sum + item.quantity, 0);

        if (items.length === 0) {
            cartItemsContainer.innerHTML = '<p>Your cart is empty.</p>';
            return;
        }

        cartItemsContainer.innerHTML = items.map(item => `
            <div class="d-flex justify-content-between align-items-center mb-2">
                <span>${item.product.name} (x${item.quantity})</span>
                <button class="btn btn-sm btn-danger remove-from-cart-btn" data-product-id="${item.product.id}">Remove</button>
            </div>
        `).join('');
    };

    const loadCart = async () => {
        if (!selectedCustomerId) return;
        try {
            const response = await fetch(`${API_BASE_URL}/carts/${selectedCustomerId}`);
            const cart = await response.json();
            updateCartUI(cart);
        } catch (error) {
            console.error('Error loading cart:', error);
            updateCartUI({ items: [] });
        }
    };

    const addProductToCart = async (productId) => {
        if (!selectedCustomerId) {
            alert('Please select a customer first.');
            return;
        }
        try {
            const response = await fetch(`${API_BASE_URL}/carts/${selectedCustomerId}/products/${productId}`, { method: 'POST' });
            const cart = await response.json();
            updateCartUI(cart);
        } catch (error) {
            console.error('Error adding product to cart:', error);
        }
    };

    const removeProductFromCart = async (productId) => {
        if(!selectedCustomerId) return;
        try {
            const response = await fetch(`${API_BASE_URL}/carts/${selectedCustomerId}/products/${productId}`, { method: 'DELETE' });
            const cart = await response.json();
            updateCartUI(cart);
        } catch (error) {
            console.error('Error removing product from cart:', error);
        }
    };

    const clearCart = async () => {
        if (!selectedCustomerId) return;
        try {
            await fetch(`${API_BASE_URL}/carts/${selectedCustomerId}`, { method: 'DELETE' });
            updateCartUI({ items: [] });
        } catch (error) {
            console.error('Error clearing cart:', error);
        }
    };

    customerSelector.addEventListener('change', (e) => {
        selectedCustomerId = e.target.value;
        loadCart();
    });

    productList.addEventListener('click', (e) => {
        if (e.target.classList.contains('add-to-cart-btn')) {
            addProductToCart(e.target.dataset.productId);
        }
    });

    cartItemsContainer.addEventListener('click', (e) => {
        if (e.target.classList.contains('remove-from-cart-btn')) {
            removeProductFromCart(e.target.dataset.productId);
        }
    });

    clearCartBtn.addEventListener('click', clearCart);

    loadCustomers();
    loadProducts();
});