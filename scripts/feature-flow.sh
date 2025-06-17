#!/bin/bash

# 🎨 Colores
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # Sin color

# 📥 Parámetros
FEATURE_NAME=$1
HU_ID=$2
VERSION=$3
TAG_MESSAGE=$4

# 🛑 Validación
if [ -z "$FEATURE_NAME" ] || [ -z "$HU_ID" ]; then
  echo -e "${RED}❌ Uso: ./scripts/feature-flow.sh <nombre-feature> <HU_ID> [version] [mensaje-tag]${NC}"
  exit 1
fi

# 🧠 Armar nombre de rama
BRANCH_NAME="feature/hu-${HU_ID}/${FEATURE_NAME}"

# 🌱 Crear rama
echo -e "${YELLOW}➡️  Creando rama: ${GREEN}$BRANCH_NAME${NC}"
git checkout -b "$BRANCH_NAME"
echo -e "${GREEN}✅ Rama creada. Trabaja y haz tus commits.${NC}"

echo ""
read -p "⏸️  Presiona ENTER cuando hayas terminado y quieras hacer merge a main..."

# 🔁 Cambiar a main y traer últimos cambios
echo -e "${YELLOW}🔁 Cambiando a main y haciendo pull...${NC}"
git checkout main
git pull origin main

# 🔀 Merge
echo -e "${YELLOW}🔀 Haciendo merge de ${GREEN}$BRANCH_NAME${NC} a ${GREEN}main${NC}..."
git merge --no-ff "$BRANCH_NAME"

# 📤 Push a main
echo -e "${YELLOW}📤 Subiendo cambios a main...${NC}"
git push origin main

# 🏷️ Crear tag si se especificó versión
if [ -n "$VERSION" ]; then
  echo -e "${YELLOW}🏷️  Creando tag: ${GREEN}$VERSION${NC}"
  git tag -a "$VERSION" -m "$TAG_MESSAGE"
  git push origin "$VERSION"
fi

# 🧹 Preguntar si quieres borrar la rama
read -p "🧹 ¿Deseas eliminar la rama local y remota? (s/n): " DELETE
if [[ "$DELETE" == "s" || "$DELETE" == "S" ]]; then
  git branch -d "$BRANCH_NAME"
  git push origin --delete "$BRANCH_NAME"
  echo -e "${GREEN}🧹 Rama eliminada local y remotamente.${NC}"
fi

echo -e "${GREEN}✅ ¡Flujo completado exitosamente, crack! 💪${NC}"
