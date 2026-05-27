package br.com.escola.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.exception.VerificarExisteException;
import br.com.escola.biblioteca.repository.EditoraRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

@Service
public class EditoraService {
   
  

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    

    public List<EditoraResponseDTO> listarEditoras() {
        List<Editora> editoras = editoraRepository.findAll();
        return editoras.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

       
 
    public EditoraResponseDTO obterEditoraPorId(Long id) {
        Editora editora = buscarEditoraPorId(id);
        return new EditoraResponseDTO(id, editora.getNome(),editora.getCnpj(), editora.getEstado());
    }

    public EditoraResponseDTO salvar(EditoraRequestDTO dto) {
        if (editoraRepository.findByCnpj(dto.cnpj()).isPresent()) {
        throw new VerificarExisteException("Não foi possível salvar. O CNPJ '" + dto.cnpj() + "' já está cadastrado.");
        }
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado());
        return mapToResponseDTO(editoraRepository.save(editora));
    }

public EditoraResponseDTO atualizar(Long id, EditoraRequestDTO dto) {
        if (!editoraRepository.existsById(id)) {
            throw new VerificarExisteException("Não foi possível atualizar a editora. Editora não encontrada com id: " + id);
        }

        Optional<Editora> editoraComMesmoCnpj = editoraRepository.findByCnpj(dto.cnpj());
        if (editoraComMesmoCnpj.isPresent() && !editoraComMesmoCnpj.get().getId().equals(id)) {
            throw new VerificarExisteException("Não foi possível atualizar. O CNPJ '" + dto.cnpj() + "' já pertence a outra editora.");
        }

        Editora editora = buscarEditoraPorId(id);
        copiarDadosParaEntidade(editora, dto);

        return mapToResponseDTO(editoraRepository.save(editora));
    }

public void deletar(Long id) {
        if (!editoraRepository.existsById(id)) {
            throw new VerificarExisteException("Não foi possível deletar a editora. Editora não encontrada com id: " + id);
        }

        if (livroRepository.existsByEditoraId(id)) {
            throw new VerificarExisteException("Não é possível remover a editora. Existem livros vinculados a ela.");
        }

        editoraRepository.deleteById(id);
    }



   private Editora buscarEditoraPorId(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new VerificarExisteException("Editora não encontrada com o id: " + id));
    }

    private void copiarDadosParaEntidade(Editora editora, EditoraRequestDTO dto) {
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado());
    }

         private EditoraResponseDTO mapToResponseDTO(Editora editora) {
        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getCnpj(),
                editora.getEstado());
    }

    




}
