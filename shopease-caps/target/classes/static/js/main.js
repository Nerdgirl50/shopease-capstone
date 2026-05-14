// ====================================
// ShopEase - Main JavaScript
// ====================================

document.addEventListener('DOMContentLoaded', function () {

  // --- Auto-dismiss alerts after 4 seconds ---
  const alerts = document.querySelectorAll('.alert');
  alerts.forEach(alert => {
    setTimeout(() => {
      alert.style.transition = 'opacity 0.5s ease';
      alert.style.opacity = '0';
      setTimeout(() => alert.remove(), 500);
    }, 4000);
  });

  // --- Cart quantity change: auto-submit ---
  document.querySelectorAll('.cart-item-qty input').forEach(input => {
    input.addEventListener('change', function () {
      this.closest('form').submit();
    });
  });

  // --- Add to cart button feedback ---
  document.querySelectorAll('.add-to-cart-form').forEach(form => {
    form.addEventListener('submit', function (e) {
      const btn = this.querySelector('button');
      if (btn) {
        btn.innerHTML = '<i class="fas fa-check"></i> Added!';
        btn.style.background = '#10b981';
      }
    });
  });

  // --- Confirm delete actions ---
  document.querySelectorAll('form[data-confirm]').forEach(form => {
    form.addEventListener('submit', function (e) {
      if (!confirm(this.dataset.confirm)) e.preventDefault();
    });
  });

  // --- Product image error fallback ---
  document.querySelectorAll('img').forEach(img => {
    img.addEventListener('error', function () {
      this.src = 'https://via.placeholder.com/300x250?text=No+Image';
    });
  });

  // --- Admin: highlight active nav ---
  const currentPath = window.location.pathname;
  document.querySelectorAll('.nav-links a').forEach(link => {
    if (link.getAttribute('href') === currentPath) {
      link.style.background = 'var(--bg)';
      link.style.color = 'var(--primary)';
    }
  });

  // --- Smooth scroll to top on page load ---
  window.scrollTo({ top: 0, behavior: 'smooth' });

  console.log('%c🛍️ ShopEase Loaded Successfully!', 'color:#2563eb;font-size:14px;font-weight:bold;');
});
