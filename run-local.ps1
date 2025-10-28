$envFile = Join-Path $PSScriptRoot ".env"
Get-Content $envFile |
  Where-Object {$_ -notmatch '^\s*#' -and $_ -match '='} |
  ForEach-Object {
    $name,$value = $_.Split('=',2)
[Environment]::SetEnvironmentVariable($name.Trim(), $value.Trim(), 'Process')
  }

Push-Location $PSScriptRoot
try {
  & ".\mvnw.cmd" spring-boot:run
} finally {
  Pop-Location
}
