# UrbanEscape – Arquitectura (MVP)

## Flujo principal
Onboarding -> Login -> City Select -> Adventure List -> Adventure Detail -> Game (placeholder)

## Capas (paquetes)
- ui/ : pantallas Compose + componentes
- navigation/ : rutas y grafo de navegación
- domain/ : modelos y casos de uso
- data/ : repositorios e implementaciones (mock ahora, Firebase después)

## Reglas
- Nada de secretos en el repo (no google-services.json, no keystores).
- El contenido (ciudades/aventuras/steps) debe venir del backend en el futuro (Firestore).
- PRs pequeños: una feature por PR.

## Próximos PRs
1) Bootstrap Android (Compose) compilable + navegación + pantallas mock.
2) Implementar repositorios mock y DataStore.
3) Preparar Firebase Auth/Firestore (solo deps + README de setup, sin secretos).
4) StepEngine (story/question/gps_check) + progreso.
