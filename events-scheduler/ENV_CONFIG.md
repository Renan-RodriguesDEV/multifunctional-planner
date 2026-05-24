# Configuração de Variáveis de Ambiente

## Para desenvolvimento (localhost):
Nenhuma configuração necessária. Por padrão, a aplicação usa `http://localhost:8080`.

## Para produção ou outro servidor:
Injete a URL do backend na janela antes de iniciar a app, criando um arquivo `config.js`:

### Opção 1: Via script HTML
```html
<!-- No index.html antes do script da app -->
<script>
  window['API_BASE_URL'] = 'https://seu-backend.com';
</script>
```

### Opção 2: Via variável de ambiente no ng serve
```bash
# Não é possível diretamente com ng serve, use a opção 1 ou configure um proxy alternativo
```

### Opção 3: Modificar config.ts em build time
Edite `src/app/config.ts` e defina a URL desejada.
