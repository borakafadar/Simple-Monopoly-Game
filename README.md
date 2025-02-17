# Simple Monopoly Game

_ps. this was a cs102 lab assignment_
_pps. finished in 7:20 hours_

## Simple Monopoly Game

**Simplified console-based Monopoly game** where the user competes against 1 to 3 computer players.

### Game Setup

- The user determines the number of computer players and their names.
- The user also enters their own name.
- All players start with **10 coins**.
- The **playing order is randomized** at the beginning.
- Players roll a **6-sided die** to move across the board.
- All players start at position `0` on the map.
- Multiple players can occupy the same position.

### Game Board

![Monopoly Board](https://github.com/user-attachments/assets/c13083e6-54b4-492a-89b6-79dadc363557)

### Special Events

- **`0`**: Players passing this position earn **3 coins**. Landing exactly earns **6 coins**.
- **`1`**: Players roll a die and:
  - `1`: Lose 2 coins
  - `2`: Lose 1 coin
  - `3`: Move 1 space forward
  - `4`: Move 2 spaces forward
  - `5`: Receive 1 coin and move 1 space forward
  - `6`: Receive 2 coins and move 2 spaces forward
- **`2`**: Receives 1 coin from each remaining player.
- **`3`**: Skips the next turn.

### Properties & Costs

| Property | Price | House Cost | Rent by House Count |
|----------|--------|------------|------------------------|
| A, B, C  | 2      | 1          | 1 (0), 2 (1), 3 (2), 4 (3), 6 (4) |
| D, E, F  | 4      | 1          | 2 (0), 2 (1), 3 (2), 3 (3), 7 (4) |
| G, H, I  | 6      | 2          | 1 (0), 3 (1), 4 (2), 6 (3), 7 (4) |
| J, K, L  | 8      | 3          | 3 (0), 3 (1), 6 (2), 6 (3), 9 (4) |

- `Rent` depends on the number of houses.
- If a player lands on an **unowned property**, they can **buy** it if they have enough coins.
- If owned, they **pay rent** to the owner.

### Player Actions Per Turn

1. **Roll the die and move**.
2. **Buy property** (if available and unowned).
3. **Pay rent** (if on an owned property).
4. **Special event triggers** (if landing on a special cell).
5. **Build a house** (if owning a property and having enough coins).
6. **Sell a property** (if needed for coins; houses are removed upon selling).

### Losing the Game

- If a player **cannot pay rent**, they lose the game.
- The **bank covers unpaid rent** (it has unlimited coins).
- Eliminated players **stop moving and are removed from the map**.
- The game continues until **only one player remains** or the **100-turn limit is reached**.

### Map Representation Example

```plaintext
|0...|A.t0|B...|C.t1|1...|
|....|....|....|tn..|u...|
|L.n0|              |D.u1|
|....|              |....|
|K.n0|              |E...|
|....|              |....|
|J.n0|              |F...|
|....|              |b...|
|3...|I.t0|H...|G...|2...|
|....|....|....|....|....|
```

