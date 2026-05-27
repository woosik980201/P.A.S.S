#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

char board[3][3];
char current = 'O';   // 현재 차례 (O 부터 시작)

// 보드를 1~9 숫자로 초기화 (사용자가 위치 선택하기 쉽게)
void init_board(void) {
    char n = '1';
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            board[i][j] = n++;
}

// 보드 출력
void print_board(void) {
    printf("\n");
    for (int i = 0; i < 3; i++) {
        printf("   %c | %c | %c \n", board[i][0], board[i][1], board[i][2]);
        if (i < 2) printf("  ---+---+---\n");
    }
    printf("\n");
}

// 승리 검사: 8개 라인 중 하나라도 3개 일치하면 1 반환
int check_win(void) {
    // 가로 3줄 + 세로 3줄
    for (int i = 0; i < 3; i++) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return 1;
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return 1;
    }
    // 대각선 2줄
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return 1;
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return 1;
    return 0;
}

int main(void) {
    init_board();
    int turn = 0;

    while (turn < 9) {
        print_board();
        printf("  Player %c, position (1-9): ", current);

        int pos;
        scanf("%d", &pos);

        // 범위 체크
        if (pos < 1 || pos > 9) {
            printf("  ! Out of range.\n");
            continue;
        }

        // 1~9를 (row, col)로 변환 — 핵심 트릭
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        // 이미 놓인 자리인지 체크
        if (board[row][col] == 'O' || board[row][col] == 'X') {
            printf("  ! Already taken.\n");
            continue;
        }

        // 마크 놓기
        board[row][col] = current;
        turn++;

        // 승리 검사
        if (check_win()) {
            print_board();
            printf("  *** Player %c WINS! ***\n\n", current);
            return 0;
        }

        // 차례 바꾸기
        current = (current == 'O') ? 'X' : 'O';
    }

    // 9칸이 다 차도 승자 없으면 무승부
    print_board();
    printf("  *** DRAW ***\n\n");
    return 0;
}