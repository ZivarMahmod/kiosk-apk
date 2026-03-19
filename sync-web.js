// Kopierar webbfiler till www/ inför Capacitor-byggning
const fs = require("fs");
const path = require("path");

const SRC = __dirname;
const DEST = path.join(__dirname, "www");

// Filer att kopiera
const FILES = ["index.html", "manifest.json", "icon-192.png", "icon-512.png"];

// Skapa www/ om den inte finns
if (!fs.existsSync(DEST)) fs.mkdirSync(DEST);

// Kopiera filer
FILES.forEach(f => {
  const src = path.join(SRC, f);
  if (fs.existsSync(src)) {
    fs.copyFileSync(src, path.join(DEST, f));
    console.log("✓ " + f);
  }
});

// Kopiera images/-mappen
const imgSrc = path.join(SRC, "images");
const imgDest = path.join(DEST, "images");
if (fs.existsSync(imgSrc)) {
  if (!fs.existsSync(imgDest)) fs.mkdirSync(imgDest);
  fs.readdirSync(imgSrc).forEach(f => {
    fs.copyFileSync(path.join(imgSrc, f), path.join(imgDest, f));
  });
  console.log("✓ images/");
}

console.log("Klar — www/ uppdaterad.");
