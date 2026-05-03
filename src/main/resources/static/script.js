async function fetchProducts() {
    const skinType = document.getElementById('skinTypeSelector').value;
    const grid = document.getElementById('productGrid');

    if (!skinType) return;

    try {
        const response = await fetch(`http://localhost:8080/search?type=${skinType}`);
        const products = await response.json();

        if (products.length === 0) {
            grid.innerHTML = "<p class='subtitle'>No products found for this skin type.</p>";
            return;
        }

        grid.innerHTML = products.map(product => {
            const productString = encodeURIComponent(JSON.stringify(product));
            return `
                <div class="product-card">
                    <img class="product-image" src="${product.imageUrl || 'https://via.placeholder.com/400'}" alt="Product">
                    <div class="card-content">
                        <p class="brand">${product.brand}</p>
                        <h2 class="product-name">${product.name}</h2>
                        <p class="category">${product.Category || 'Treatment'}</p>
                        <p class="description">${product.description}</p>
                    </div>
                    <div class="product-footer">
                        <span class="tag" style="border: 1px solid #eee; padding: 5px 10px; font-size: 0.7rem;">${product.targetSkinType}</span>
                        <button onclick="openModal('${productString}')" style="background:none; border:none; color:var(--gold); cursor:pointer; font-weight:600;">EXPLORE →</button>
                    </div>
                </div>
            `;
        }).join('');
    } catch (error) {
        grid.innerHTML = "<p>Error connecting to server.</p>";
    }
}

function openModal(data) {
    const product = JSON.parse(decodeURIComponent(data));
    const modal = document.getElementById('productModal');
    const body = document.getElementById('modalBody');

    body.innerHTML = `
        <div style="flex: 1;"><img src="${product.imageUrl}" style="width:100%;"></div>
        <div style="flex: 1.5;">
            <p class="brand">${product.brand}</p>
            <h2 style="font-family: 'Playfair Display', serif; font-size: 2.5rem; margin: 10px 0;">${product.name}</h2>
            <p style="color: var(--gold); font-style: italic;">${product.Category}</p>
            <p style="margin-top: 20px; line-height: 1.8;">${product.description}</p>
            <button style="width: 100%; margin-top: 30px; padding: 15px; background: var(--charcoal); color: white; border: none; letter-spacing: 2px;">ADD TO ROUTINE</button>
        </div>
    `;
    modal.style.display = "block";
}

function closeModal() { document.getElementById('productModal').style.display = "none"; }
window.onclick = function(event) { if (event.target == document.getElementById('productModal')) closeModal(); }