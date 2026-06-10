# Arquitectura Modular Backend - Simulador Mundial 2026

## Objetivo General

El sistema permitirá a los usuarios crear simulaciones del Mundial 2026 utilizando como punto de partida el estado real del torneo.

A medida que el Mundial avance, un administrador podrá registrar los resultados oficiales de los partidos para que las nuevas simulaciones comiencen desde la fase y resultados reales ya disputados.

---

# Módulo Auth

## Responsabilidad

Gestionar la autenticación y autorización de usuarios.

## Entidades

```text
Usuario
Credencial
Rol
UsuarioRol
```

## Funcionalidades

* Registro de usuarios.
* Inicio de sesión.
* Gestión de roles.
* Recuperación de contraseña.
* Generación y validación de JWT.
* Control de acceso según permisos.

## Casos de uso

```text
Iniciar sesión
Cerrar sesión
Registrar usuario
Asignar rol
```

---

# Módulo Mundial

## Responsabilidad

Administrar la información base y oficial del Mundial.

## Entidades

```text
Pais
Equipo
Fase
Estadio
```

## Funcionalidades

* Consultar selecciones participantes.
* Consultar estadios.
* Consultar fases del torneo.
* Gestionar datos maestros del Mundial.

## Casos de uso

```text
Consultar equipos
Consultar estadios
Consultar fases
```

---

# Módulo Resultados

## Responsabilidad

Administrar los resultados oficiales del Mundial.

Este módulo representa el torneo real.

Los administradores registran aquí los resultados oficiales a medida que avanza la competencia.

## Entidades

```text
PartidoOficial
ParticipantePartidoOficial
```

## Funcionalidades

* Registrar resultados oficiales.
* Actualizar resultados oficiales.
* Consultar calendario oficial.
* Consultar tabla de posiciones oficial.
* Consultar clasificados oficiales.

## Casos de uso

```text
Registrar resultado oficial
Actualizar marcador
Consultar resultados
Consultar clasificación oficial
```

---

# Módulo Simulación

## Responsabilidad

Gestionar las simulaciones realizadas por los usuarios.

Cada simulación es independiente.

Los resultados registrados dentro de una simulación no afectan al Mundial oficial ni a otras simulaciones.

## Entidades

```text
Simulacion
SimulacionEquipo
Partido
ParticipantePartido
Grupo
```

## Funcionalidades

* Crear simulación.
* Eliminar simulación.
* Consultar simulaciones.
* Registrar resultados simulados.
* Continuar simulaciones existentes.

## Casos de uso

```text
Crear simulación
Simular partido
Consultar simulación
Eliminar simulación
```

---

# Módulo Motor

## Responsabilidad

Implementar toda la lógica de negocio del Mundial.

Este módulo funciona como el cerebro del sistema.

## Entidades

No requiere entidades propias.

Generalmente se implementa mediante servicios.

```text
MotorSimulacionService
MotorClasificacionService
MotorEliminacionService
```

## Funcionalidades

* Calcular tabla de grupos.
* Determinar clasificados.
* Aplicar criterios de desempate FIFA.
* Generar cruces eliminatorios.
* Generar octavos de final.
* Generar cuartos de final.
* Generar semifinales.
* Generar final.
* Determinar campeón.

## Casos de uso

```text
Calcular posiciones
Generar cruces
Determinar campeón
Actualizar clasificación
```

---

# Flujo General

```text
Administrador
    |
    v
Resultados Oficiales
    |
    v
Estado Real Del Mundial
    |
    v
Usuario Crea Simulación
    |
    v
Se Copian Los Resultados Oficiales
    |
    v
Motor De Simulación
    |
    v
Calcula Clasificación
    |
    v
Genera Fases Siguientes
```

---

# Nuevas Entidades Del MER

## PartidoOficial

Representa los partidos reales del Mundial.

```sql
Table PartidoOficial {
  id_partido_oficial bigint [pk, increment]
  id_fase bigint [not null]
  id_estadio bigint [not null]
  fecha datetime [not null]
  estado enum('PENDIENTE', 'EN_JUEGO', 'FINALIZADO') [not null]
}
```

## ParticipantePartidoOficial

Representa los equipos participantes y el resultado oficial de cada partido.

```sql
Table ParticipantePartidoOficial {
  id_participante_partido_oficial bigint [pk, increment]
  id_partido_oficial bigint [not null]
  id_equipo bigint [not null]
  goles int [not null, default: 0]
  resultado enum('GANADOR', 'PERDEDOR', 'EMPATE')
}
```

---

# Relaciones Nuevas

```sql
Ref: PartidoOficial.id_fase > Fase.id_fase

Ref: PartidoOficial.id_estadio > Estadio.id_estadio

Ref: ParticipantePartidoOficial.id_partido_oficial > PartidoOficial.id_partido_oficial

Ref: ParticipantePartidoOficial.id_equipo > Equipo.id_equipo
```

---

# Integración Con El MER Actual

## Mundial Oficial

```text
Fase
  |
PartidoOficial
  |
ParticipantePartidoOficial
  |
Equipo
```

Representa los resultados reales del Mundial.

---

## Simulaciones

```text
Simulacion
   |
Grupo
   |
Equipo
   |
Partido
   |
ParticipantePartido
```

Representa las predicciones realizadas por cada usuario.

---

# Beneficio De La Separación

Permite que:

1. El administrador actualice resultados reales.
2. El sistema conozca el estado actual del Mundial.
3. Los usuarios creen simulaciones desde cualquier punto del torneo.
4. Varias simulaciones puedan coexistir sin afectar los resultados oficiales.
5. El motor pueda generar automáticamente las fases restantes a partir de los resultados oficiales registrados.

```
```
