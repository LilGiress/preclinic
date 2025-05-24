// Fichier: main.js
document.addEventListener("DOMContentLoaded", function () {
  const input = document.getElementById("searchInput");
  const rows = document.querySelectorAll("table tbody tr");

  input.addEventListener("keyup", function () {
    const filter = input.value.toLowerCase();
    rows.forEach(row => {
      const fileName = row.children[1].textContent.toLowerCase();
      row.style.display = fileName.includes(filter) ? "" : "none";
    });
  });
});


document.addEventListener("DOMContentLoaded", function () {
  const modal = document.getElementById("previewModal");
  const previewArea = document.getElementById("previewArea");
  const closeBtn = document.querySelector(".modal .close");

  // Gestion du clic sur les icônes "œil"
  document.querySelectorAll(".fa-eye").forEach((icon, index) => {
    icon.addEventListener("click", () => {
      const rows = document.querySelectorAll("table tbody tr");
      const fileName = rows[index].children[1].textContent.trim();
      const fileType = fileName.split('.').pop().toLowerCase();

      // Prévisualisation simple selon le type
      if (["jpg", "jpeg", "png", "gif"].includes(fileType)) {
        previewArea.innerHTML = `<img src="assets/portrait.jpg" style="max-width:100%; border-radius:10px;" />`;
      } else if (fileType === "pdf") {
        previewArea.innerHTML = `<iframe src="assets/sample.pdf" width="100%" height="500px"></iframe>`;
      } else {
        previewArea.innerHTML = `<p>Aperçu non disponible pour ce type de fichier : ${fileType}</p>`;
      }

      modal.style.display = "block";
    });
  });

  // Fermer la modale
  closeBtn.onclick = () => modal.style.display = "none";
  window.onclick = (e) => {
    if (e.target === modal) modal.style.display = "none";
  };
});
