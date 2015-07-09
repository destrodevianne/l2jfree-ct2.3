/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jfree.gameserver.network.serverpackets;

/**
 * FE 70 00 (opcodes)
 * A2 11 00 00
 * 08 00 00 00
 * 04 00 00 00
 * 
 * 14 47 41 4D 49 4E 47 53 54 41 54 45 53 48 4F 52 54 43 55 54 00 0C 47 41 4D 49 4E 47 53 54 41 54 45 00
 * 
 * 53 00 00 00 (loop size, each step 5x D)
 * 
 * 3B 00 00 00
 * 43 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 3C 00 00 00
 * 42 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 3E 00 00 00
 * 4A 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 3F 00 00 00
 * 4E 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 40 00 00 00
 * 54 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 41 00 00 00
 * 48 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 42 00 00 00
 * 56 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 43 00 00 00
 * 52 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 44 00 00 00
 * 4B 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 4E 00 00 00
 * 4D 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 46 00 00 00
 * 4D 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 47 00 00 00
 * 4F 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 48 00 00 00
 * 50 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 49 00 00 00
 * 55 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 4A 00 00 00
 * 52 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 4B 00 00 00
 * 53 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 4C 00 00 00
 * 58 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 4D 00 00 00
 * 49 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 4F 00 00 00
 * BE 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 50 00 00 00
 * 45 00 00 00
 * 12 00 00 00
 * 10 00 00 00
 * 01 00 00 00
 * 
 * 51 00 00 00
 * 4C 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 01 00 00 00
 * 21 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 02 00 00 00
 * 22 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 03 00 00 00
 * 1B 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 04 00 00 00
 * 0D 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 05 00 00 00
 * 09 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 06 00 00 00
 * 48 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 07 00 00 00
 * 57 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0A 00 00 00
 * 46 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0B 00 00 00
 * 50 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0C 00 00 00
 * 59 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 31 00 00 00
 * 70 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 32 00 00 00
 * 71 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 33 00 00 00
 * 72 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 34 00 00 00
 * 73 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 35 00 00 00
 * 74 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 36 00 00 00
 * 75 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 37 00 00 00
 * 76 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 38 00 00 00
 * 77 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 39 00 00 00
 * 78 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 3A 00 00 00
 * 79 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0D 00 00 00
 * 70 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0E 00 00 00
 * 71 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 0F 00 00 00
 * 72 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 10 00 00 00
 * 73 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 11 00 00 00
 * 74 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 12 00 00 00
 * 75 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 13 00 00 00
 * 76 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 14 00 00 00
 * 77 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 15 00 00 00
 * 78 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 16 00 00 00
 * 79 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 17 00 00 00
 * 7A 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 18 00 00 00
 * 7B 00 00 00
 * 00 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 19 00 00 00
 * 31 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1A 00 00 00
 * 32 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1B 00 00 00
 * 33 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1C 00 00 00
 * 34 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1D 00 00 00
 * 35 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1E 00 00 00
 * 36 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 1F 00 00 00
 * 37 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 20 00 00 00
 * 38 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 21 00 00 00
 * 39 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 22 00 00 00
 * 30 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 23 00 00 00
 * BD 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 24 00 00 00
 * BB 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 25 00 00 00
 * 61 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 26 00 00 00
 * 62 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 27 00 00 00
 * 63 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 28 00 00 00
 * 64 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 29 00 00 00
 * 65 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 2A 00 00 00
 * 66 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 2B 00 00 00
 * 67 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 2C 00 00 00
 * 68 00 00 00
 * 12 00 00 00
 * 00 00 00 00
 * 01 00 00 00
 * 
 * 2D 00 00 00 69 00 00 00 12 00 00 00 00 00 00 00 01 00 00 00 2E 00 00 00 60 00 00 00 12 00 00 00 00 00 00 00 01 00 00 00 2F 00 00 00 6F 00 00 00 12 00 00 00 00 00 00 00 01 00 00 00 30 00 00 00 6A 00 00 00 12 00 00 00 00 00 00 00 01 00 00 00 5C 00 00 00 20 00 00 00 12 00 00 00 00 00 00 00 01 00 00 00 5D 00 00 00 21 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5E 00 00 00 22 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5F 00 00 00 24 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 59 00 00 00 23 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 56 00 00 00 2E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 12 54 45 4D 50 53 54 41 54 45 53 48 4F 52 54 43 55 54 00 00 39 00 00 00 3B 00 00 00 43 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3C 00 00 00 42 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3F 00 00 00 4E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 40 00 00 00 54 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 42 00 00 00 56 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 43 00 00 00 52 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 44 00 00 00 4B 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 46 00 00 00 4D 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 49 00 00 00 55 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4C 00 00 00 58 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4F 00 00 00 BE 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 50 00 00 00 45 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 0D 00 00 00 70 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 0E 00 00 00 71 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 0F 00 00 00 72 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 10 00 00 00 73 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 11 00 00 00 74 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 12 00 00 00 75 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 13 00 00 00 76 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 14 00 00 00 77 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 15 00 00 00 78 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 16 00 00 00 79 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 17 00 00 00 7A 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 18 00 00 00 7B 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 19 00 00 00 31 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1A 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1B 00 00 00 33 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1C 00 00 00 34 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1D 00 00 00 35 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1E 00 00 00 36 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 1F 00 00 00 37 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 20 00 00 00 38 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 21 00 00 00 39 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 22 00 00 00 30 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 23 00 00 00 BD 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 24 00 00 00 BB 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 25 00 00 00 61 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 26 00 00 00 62 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 27 00 00 00 63 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 28 00 00 00 64 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 29 00 00 00 65 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2A 00 00 00 66 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2B 00 00 00 67 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2C 00 00 00 68 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2D 00 00 00 69 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2E 00 00 00 60 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 2F 00 00 00 6F 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 30 00 00 00 6A 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 52 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 53 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 54 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 55 00 00 00 53 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 56 00 00 00 2E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 60 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 61 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 62 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 63 00 00 00 53 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 14 46 4C 49 47 48 54 53 54 41 54 45 53 48 4F 52 54 43 55 54 00 00 27 00 00 00 3B 00 00 00 43 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3C 00 00 00 42 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3F 00 00 00 4E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 40 00 00 00 54 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 42 00 00 00 56 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 43 00 00 00 52 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 44 00 00 00 4B 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 46 00 00 00 4D 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 49 00 00 00 55 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4C 00 00 00 58 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4F 00 00 00 BE 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 80 00 00 00 31 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 81 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 82 00 00 00 33 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 83 00 00 00 34 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 84 00 00 00 35 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 85 00 00 00 36 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 86 00 00 00 37 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 87 00 00 00 38 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 88 00 00 00 39 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 89 00 00 00 30 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8A 00 00 00 BD 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8B 00 00 00 BB 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8C 00 00 00 20 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 56 00 00 00 2E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 52 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 53 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 54 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 98 00 00 00 53 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8F 00 00 00 45 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 90 00 00 00 51 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 60 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 61 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 62 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 93 00 00 00 45 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 94 00 00 00 51 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 5D 00 00 00 21 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5E 00 00 00 22 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5F 00 00 00 24 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 18 46 4C 49 47 48 54 54 52 41 4E 53 46 4F 52 4D 53 48 4F 52 54 43 55 54 00 00 28 00 00 00 3B 00 00 00 43 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3C 00 00 00 42 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 3F 00 00 00 4E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 40 00 00 00 54 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 42 00 00 00 56 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 43 00 00 00 52 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 44 00 00 00 4B 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 46 00 00 00 4D 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 49 00 00 00 55 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4C 00 00 00 58 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 4F 00 00 00 BE 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 74 00 00 00 31 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 75 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 76 00 00 00 33 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 77 00 00 00 34 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 78 00 00 00 35 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 79 00 00 00 36 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7A 00 00 00 37 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7B 00 00 00 38 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7C 00 00 00 39 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7D 00 00 00 30 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7E 00 00 00 BD 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 7F 00 00 00 BB 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8C 00 00 00 20 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 56 00 00 00 2E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 91 00 00 00 6A 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 52 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 53 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 54 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 97 00 00 00 53 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8D 00 00 00 45 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 8E 00 00 00 51 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 60 00 00 00 41 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 61 00 00 00 44 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 62 00 00 00 57 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 93 00 00 00 45 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 94 00 00 00 51 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 5D 00 00 00 21 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5E 00 00 00 22 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 5F 00 00 00 24 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 11 00 00 00 10 00 00 00 
 */
public class ExUiSetting extends L2GameServerPacket
{
	private static final String _S__EXUISETTING = "[S] FE:70 ExUISetting ch[unk]";
	
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x70);
		
		// 4514
		// 8
		// 4
		// 
	}
	
	@Override
	public String getType()
	{
		return _S__EXUISETTING;
	}
}
