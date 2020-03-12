package sudoku.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sudoku.model.UIBoardLayout;
import sudoku.service.BoardService;

@RestController
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public UIBoardLayout board(){
        return boardService.retrieveBoard();
    }

    @PostMapping("/board/validate")
    public ResponseEntity board(@RequestBody UIBoardLayout board){
        boolean valid =boardService.validate(board);
        if (valid){
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
