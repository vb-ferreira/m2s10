package com.fmt.m2s10.domain.model;

public class ErrorResponse {
	
	private Integer status;
	
	private String descricao;
	
	private String mensagem;

	public ErrorResponse(Integer status, String descricao, String mensagem) {
		this.status = status;
		this.descricao = descricao;
		this.mensagem = mensagem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
